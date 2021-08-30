package com.code9.tenniscourt.dto;

import com.code9.tenniscourt.model.TennisCourt;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Value
@Builder
public class ReservationDto {

    Long id;

    @FutureOrPresent(message = "Reservation start time should be in present or future")
    LocalDateTime startTime;

    @Future(message = "Reservation end time should be in present or future")
    LocalDateTime endTime;

    @PositiveOrZero(message = "Player id should be positive or zero")
    Long playerId;

    @PositiveOrZero(message = "Tennis court id should be positive or zero")
    Long tennisCourt;

}
