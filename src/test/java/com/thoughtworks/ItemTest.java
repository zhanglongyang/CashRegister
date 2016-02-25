package com.thoughtworks;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lyzhang on 2/25/16.
 */
public class ItemTest {
    Item item;

    @Before
    public void setup() {
        item = new Item();
    }

    @Test
    public void item_should_have_bar_code() {
        item.setBarCode("ITEM000001");

        assertThat(item.getBarCode(), is("ITEM000001"));
    }

    @Test
    public void item_should_have_name() {
        item.setName("Coca Cola");

        assertThat(item.getName(), is("Coca Cola"));
    }

    @Test
    public void item_should_have_price() {
        item.setPrice(3.00);

        assertThat(item.getPrice(), is(3.00));
    }

    @Test
    public void item_should_have_count() {
        item.setCount(3);

        assertThat(item.getCount(), is(3));
    }
}
