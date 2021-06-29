package com.code9.payment.enumeration;

public enum PaymentMethod {

    CASH("C"), CREDIT_CARD("CC");

    private String shortName;

    private PaymentMethod(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static PaymentMethod fromShortName(String shortName) {
        switch (shortName) {
            case "C":
                return PaymentMethod.CASH;
            case "CC":
                return PaymentMethod.CREDIT_CARD;
            default:
                throw new IllegalArgumentException(String.format("ShortName [%s] not supported.", shortName));
        }
    }

}
