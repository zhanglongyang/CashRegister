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
    public void item_should_have_count() {
        item.setCount(3);

        assertThat(item.getCount(), is(3));
    }

    @Test
    public void item_should_identify_whether_has_gift() {
        item.setHasGift(true);

        assertThat(item.hasGift(), is(true));
    }

    @Test
    public void item_should_identify_whether_has_discount() {
        item.setHasDiscount(false);

        assertThat(item.hasDiscount(), is(false));
    }

    @Test
    public void subtotal_should_be_price_when_count_is_1() {
        item.setCount(1);
        item.setPrice(3.00);

        assertThat(item.calculateSubtotal(), is(3.00));
    }

    @Test
    public void subtotal_should_be_double_price_when_count_is_2() {
        item.setCount(2);
        item.setPrice(3.00);

        assertThat(item.calculateSubtotal(), is(6.00));
    }

    @Test
    public void subtotal_should_be_triple_price_when_count_is_3_and_without_discount_and_gift() {
        item.setCount(3);
        item.setPrice(3.00);
        item.setHasGift(false);
        item.setHasDiscount(false);

        assertThat(item.calculateSubtotal(), is(9.00));
    }

    @Test
    public void subtotal_should_be_double_price_when_count_is_2_and_with_gift() {
        item.setCount(3);
        item.setPrice(3.00);
        item.setHasGift(true);

        assertThat(item.calculateSubtotal(), is(6.00));
    }

    @Test
    public void subtotal_should_be_subtract_discount_when_has_discount() {
        item.setCount(2);
        item.setPrice(3.00);
        item.setHasDiscount(true);

        assertThat(item.calculateSubtotal(), is(5.70));
    }

    @Test
    public void gift_should_have_higher_priority_when_both_gift_and_discount() {
        item.setCount(5);
        item.setPrice(3.00);
        item.setHasGift(true);
        item.setHasDiscount(true);

        assertThat(item.calculateSubtotal(), is(12.00));
    }

    @Test
    public void should_show_as_payment_list_correctly() {
        item.setName("可口可乐");
        item.setCount(2);
        item.setUnit("瓶");
        item.setPrice(3.00);

        assertThat(item.toString(), is("名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)"));
    }

    @Test
    public void should_show_as_payment_list_correctly_when_has_discount() {
        item.setName("苹果");
        item.setCount(2);
        item.setUnit("斤");
        item.setPrice(5.50);
        item.setHasDiscount(true);

        assertThat(item.toString(), is("名称：苹果，数量：2斤，单价：5.50(元)，小计：10.45(元)，节省0.55(元)"));
    }
}
