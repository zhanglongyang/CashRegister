package com.thoughtworks.models;

import com.thoughtworks.discounts.Buy2Give1Discount;
import com.thoughtworks.discounts.PercentageDiscount;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

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
    public void item_should_have_unit() {
        item.setUnit("瓶");

        assertThat(item.getUnit(), is("瓶"));
    }

    @Test
    public void item_should_have_price() {
        item.setPrice(3.00);

        assertThat(item.getPrice(), is(3.00));
    }

    @Test
    public void item_should_identify_whether_has_gift() {
        item.setHasGift(true);

        assertThat(item.hasGift(), is(true));
    }

    @Test
    public void read_discount_config_with_single_discount() {
        item.setDiscounts("Buy2Give1Discount");

        assertThat(item.getDiscounts().size(), is(1));
        assertThat(item.getDiscounts().get(0), instanceOf(Buy2Give1Discount.class));
    }

    @Test
    public void read_discount_config_with_two_discounts() {
        item.setDiscounts("Buy2Give1Discount,PercentageDiscount");

        assertThat(item.getDiscounts().size(), is(2));
        assertThat(item.getDiscounts().get(0), instanceOf(Buy2Give1Discount.class));
        assertThat(item.getDiscounts().get(1), instanceOf(PercentageDiscount.class));
    }
}
