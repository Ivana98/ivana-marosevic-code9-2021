package com.code9.payment.enumeration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {

    @Override
    public String convertToDatabaseColumn(PaymentMethod method) {
        return method.getShortName();
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String dbData) {
        return PaymentMethod.fromShortName(dbData);
    }

}
