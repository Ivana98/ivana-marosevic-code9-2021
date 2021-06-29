package com.code9.payment.dto;

import com.code9.payment.Payment;
import com.code9.payment.enumeration.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {

    public Payment dtoToEntity(CreditCardDto dto) {
        return  Payment.builder()
                .reservationId(dto.getReservationId())
                .build();
    }

}
