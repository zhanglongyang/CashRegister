package com.thoughtworks.receipt;

public abstract class Template {
    public abstract String lineItemsSection();
    public abstract String giftItemsSection();
    public abstract String totalPrice();
    public abstract String totalSaving();

    public String header() {
        return "***<没钱赚商店>购物清单***\n";
    }

    public String delimiter() {
        return "----------------------\n";
    }

    public String footer() {
        return "**********************";
    }

    public String info() {
        StringBuilder sb = new StringBuilder();

        sb.append(header());
        sb.append(lineItemsSection());
        sb.append(giftItemsSection());
        sb.append(totalPrice());
        sb.append(totalSaving());
        sb.append(footer());

        return sb.toString();
    }
}
