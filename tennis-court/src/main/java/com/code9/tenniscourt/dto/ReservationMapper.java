package com.code9.tenniscourt.dto;

import com.code9.tenniscourt.model.Reservation;
import com.code9.tenniscourt.model.TennisCourt;
import com.code9.tenniscourt.service.TennisCourtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationMapper {

    private final TennisCourtService tennisCourtService;

    public Reservation dtoToNewEntity(ReservationDto dto) {
        TennisCourt court = tennisCourtService.findOneById(dto.getTennisCourt());
        return Reservation.builder()
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .playerId(dto.getPlayerId())
                .tennisCourt(court)
                .build();
    }

    public ReservationDto entityToDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .playerId(reservation.getPlayerId())
                .tennisCourt(reservation.getTennisCourt().getId())
                .build();
    }
}
