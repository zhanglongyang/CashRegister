package com.thoughtworks.utils;

import java.text.DecimalFormat;

public class PriceFormatter {
    private static final String DEFAULT_FORMAT = "0.00";
    private final String format;

    public PriceFormatter() {
        this.format = DEFAULT_FORMAT;
    }

    public PriceFormatter(String format) {
        this.format = format;
    }

    public String format(double price) {
        DecimalFormat df = new DecimalFormat(format);

        return df.format(price);
    }
}
