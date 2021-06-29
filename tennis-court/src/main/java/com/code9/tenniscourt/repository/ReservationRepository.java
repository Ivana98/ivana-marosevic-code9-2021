package com.code9.tenniscourt.repository;

import com.code9.tenniscourt.model.Reservation;
import com.code9.tenniscourt.model.TennisCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>  {

    long countByPlayerId(Long playerId);
    List<Reservation> findAllByStartTimeBetweenAndAndPlayerId(LocalDateTime startTime, LocalDateTime endTime, Long playerId);

}
