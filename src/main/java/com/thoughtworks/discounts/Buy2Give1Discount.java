package com.thoughtworks.discounts;

import com.thoughtworks.models.LineItem;

public class Buy2Give1Discount implements Discount {
    @Override
    public double subtotal(LineItem item) {
        return (item.getCount() - item.getCount() / 3) * item.getPrice();
    }
}
