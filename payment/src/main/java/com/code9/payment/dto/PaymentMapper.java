package com.code9.payment.dto;

import com.code9.payment.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDto entityToDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .reservationId(payment.getReservationId())
                .paymentMethod(payment.getPaymentMethod().toString())
                .payDate(payment.getPayDate())
                .build();
    }

}
