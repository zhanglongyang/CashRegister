package com.thoughtworks.discounts;

import com.thoughtworks.models.LineItem;

public class PercentageDiscount implements Discount {
    private static Double DISCOUNT = 0.95;
    @Override
    public double subtotal(LineItem item) {
        return item.getPrice() * item.getCount() * DISCOUNT;
    }
}
