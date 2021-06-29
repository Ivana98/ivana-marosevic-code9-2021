package com.code9.payment.dto;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Value
@Builder
public class CreditCardDto {

    Long id;

    @PositiveOrZero(message = "Id number must be positive or zero")
    Long reservationId;

    //@CreditCardNumber(message = "Credit card number is not valid")
    @Digits(integer = 16, fraction = 0, message = "Invalid format of credit card number")
    @NotNull(message = "Credit card number is mandatory")
    String creditCardNumber;

    // TODO check if credit card expired
    @Pattern(regexp = "((0[1-9])|(1[0-2]))\\/[0-9]{2}", message = "Invalid format of expiration date")
    String validDate;

    @Digits(integer = 3, fraction = 0, message = "Invalid format of CVC code")
    String cvcCode;

}
