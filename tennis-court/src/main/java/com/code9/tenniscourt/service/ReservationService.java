package com.code9.tenniscourt.service;

import com.code9.tenniscourt.exception.InvalidReservation;
import com.code9.tenniscourt.exception.NotFoundException;
import com.code9.tenniscourt.model.Reservation;
import com.code9.tenniscourt.repository.ReservationRepository;
import com.code9.tenniscourt.util.DateHelper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public boolean create(Reservation reservation) {
        validateReservation(reservation);

        boolean paymentNeeded = needToPay(reservation.getPlayerId());
        reservation.setPaymentProcessed(!paymentNeeded);

        reservationRepository.save(reservation);
        return paymentNeeded;
    }

    public void delete(Long id) {
        findOneById(id);
        reservationRepository.deleteById(id);
    }

    public Reservation findOneById(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isEmpty()) {
            throw new NotFoundException(String.format("Reservation with id '%d' does not exist", id));
        }
        return reservationOptional.get();
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Page<Reservation> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public void processPayment(Long reservationId) {
        Reservation reservation = findOneById(reservationId);
        reservation.setPaymentProcessed(true);
        reservationRepository.save(reservation);
    }

    private void validateReservation(Reservation reservation) {
        checkReservationDuration(reservation.getStartTime(), reservation.getEndTime());
        checkWorkingTime(reservation.getStartTime(), reservation.getEndTime());
        checkReservations(reservation.getPlayerId(), reservation.getStartTime(), reservation.getEndTime());
    }

    private boolean needToPay(Long playerId) {
        long playerReservations = reservationRepository.countByPlayerId(playerId);
        return playerReservations >= 5;
    }

    private void checkReservationDuration(LocalDateTime startTime, LocalDateTime endTime) {
        Duration minLength = Duration.ofMinutes(30);
        Duration maxLength = Duration.ofMinutes(120);
        Duration timeInterval = Duration.between(startTime, endTime);

        if(timeInterval.compareTo(minLength) < 0) {
            throw new InvalidReservation("Reservation duration is less than 30 minutes");
        }
        if(timeInterval.compareTo(maxLength) > 0) {
            throw new InvalidReservation("Reservation duration is greater than 2 hours");
        }
    }

    private void checkReservations(Long playerId, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime startDate = startTime.withHour(17).withMinute(0);
        LocalDateTime endDate = startTime.withHour(23).withMinute(0);

        List<Reservation> reservations = reservationRepository.findAllByStartTimeBetweenAndAndPlayerId(startDate, endDate, playerId);

        //check max number of reservations per day
        if(reservations.size() > 1) {
            throw new InvalidReservation(String.format("Player with id '%d' have reached maximal number of 2 reservation per day", playerId));
        }

        //check if reservation time overlaps with another reservation for selected day
        for (Reservation reservation: reservations) {
            if(DateHelper.isOverlapping(startTime, endTime, reservation.getStartTime(), reservation.getEndTime())) {
                throw new InvalidReservation("Reservation time overlaps with another reservation");
            }
        }
    }

    private void checkWorkingTime(LocalDateTime startTime, LocalDateTime endTime) {
        DayOfWeek day = startTime.getDayOfWeek();
        LocalDate date = startTime.toLocalDate();
        if(day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
            LocalDateTime weekendStart = date.atTime(17, 0);
            LocalDateTime weekendEnd = date.atTime(22, 0);
            if(DateHelper.isNotBetween(startTime, weekendStart, weekendEnd) ||
                    DateHelper.isNotBetween(endTime, weekendStart, weekendEnd)) {
                throw new InvalidReservation("Reservation time is beyond working time.");
            }
        }
        else {
            LocalDateTime workdayStart = date.atTime(18, 0);
            LocalDateTime workdayEnd = date.atTime(23, 0);
            if(DateHelper.isNotBetween(startTime, workdayStart, workdayEnd) ||
                    DateHelper.isNotBetween(endTime, workdayStart, workdayEnd)) {
                throw new InvalidReservation("Reservation time is beyond working time.");
            }
        }
    }

}
