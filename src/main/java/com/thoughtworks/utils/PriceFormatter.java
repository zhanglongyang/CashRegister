package com.thoughtworks.utils;

import java.text.DecimalFormat;

/**
 * Created by lyzhang on 3/1/16.
 */
public class PriceFormatter {
    private final String format;

    public PriceFormatter(String format) {
        this.format = format;
    }

    public String format(double price) {
        DecimalFormat df = new DecimalFormat(format);

        return df.format(price);
    }
}
