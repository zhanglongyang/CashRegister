package com.thoughtworks.models;

public class MemberCard {
    private String cardNo;
    private double discount;

    public MemberCard(String cardNo) {
        this.cardNo = cardNo;
    }

    public MemberCard(String cardNo, double discount) {
        this(cardNo);

        this.discount = discount;
    }

    public boolean isGolden() {
        return cardNo.endsWith("G");
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public double getDiscount() {
        return this.discount;
    }
}
