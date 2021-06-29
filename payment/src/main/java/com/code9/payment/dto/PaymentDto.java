package com.code9.payment.dto;

import com.code9.payment.enumeration.PaymentMethod;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Value
@Builder
public class PaymentDto {

    Long id;

    Long reservationId;

    String paymentMethod;

    LocalDateTime payDate;

}
