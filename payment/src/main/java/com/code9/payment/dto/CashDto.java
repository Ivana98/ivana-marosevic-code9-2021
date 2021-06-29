package com.code9.payment.dto;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.time.YearMonth;

@Value
@Builder
public class CashDto {

    Long id;

    @PositiveOrZero(message = "Id number must be positive or zero")
    Long reservationId;

}
