package com.code9.payment.dto;

import com.code9.payment.Payment;
import com.code9.payment.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CashMapper {

    public Payment dtoToEntity(CashDto dto) {
        return  Payment.builder()
                .reservationId(dto.getReservationId())
                .build();
    }

}
