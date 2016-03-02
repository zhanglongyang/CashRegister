package com.thoughtworks.utils;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lyzhang on 3/1/16.
 */
public class PriceFormatterTest {
    @Test
    public void should_format_price_correctly() {
        PriceFormatter formatter = new PriceFormatter("0.00");

        assertThat(formatter.format(2.000000), is("2.00"));
    }
}
