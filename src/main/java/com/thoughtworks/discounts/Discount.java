package com.thoughtworks.discounts;

import com.thoughtworks.models.LineItem;

public interface Discount {
    double subtotal(LineItem item);
}
