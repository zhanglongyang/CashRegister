package com.thoughtworks;

import com.thoughtworks.models.Item;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lyzhang on 3/2/16.
 */
public class PurchasedItemTest {
    PurchasedItem purchasedItem;
    Item item;

    @Before
    public void setup() {
        item = new Item("ITEM000001", "可口可乐", "瓶", 3.00);
        purchasedItem = new PurchasedItem(item);
    }

    @Test
    public void item_should_have_count() {
        purchasedItem.setCount(3);

        assertThat(purchasedItem.getCount(), is(3));
    }

    @Test
    public void subtotal_should_be_price_when_count_is_1() {
        purchasedItem.setCount(1);

        assertThat(purchasedItem.calculateSubtotal(), is(3.00));
    }

    @Test
    public void subtotal_should_be_double_price_when_count_is_2() {
        purchasedItem.setCount(2);

        assertThat(purchasedItem.calculateSubtotal(), is(6.00));
    }

    @Test
    public void subtotal_should_be_triple_price_when_count_is_3_and_without_discount_and_gift() {
        item.setHasGift(false);
        item.setHasDiscount(false);
        purchasedItem = new PurchasedItem(item);

        purchasedItem.setCount(3);

        assertThat(purchasedItem.calculateSubtotal(), is(9.00));
    }

    @Test
    public void subtotal_should_be_double_price_when_count_is_2_and_with_gift() {
        item.setHasGift(true);
        purchasedItem = new PurchasedItem(item);

        purchasedItem.setCount(3);

        assertThat(purchasedItem.calculateSubtotal(), is(6.00));
    }

    @Test
    public void subtotal_should_be_subtract_discount_when_has_discount() {
        item.setHasDiscount(true);
        purchasedItem = new PurchasedItem(item);

        purchasedItem.setCount(2);

        assertThat(purchasedItem.calculateSubtotal(), is(5.70));
    }

    @Test
    public void gift_should_have_higher_priority_when_both_gift_and_discount() {
        item.setHasDiscount(true);
        item.setHasGift(true);
        purchasedItem = new PurchasedItem(item);

        purchasedItem.setCount(5);

        assertThat(purchasedItem.calculateSubtotal(), is(12.00));
    }

    @Test
    public void should_show_as_payment_list_correctly() {
        purchasedItem.setCount(2);

        assertThat(purchasedItem.toString(), is("名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)"));
    }

    @Test
    public void should_show_as_payment_list_correctly_when_has_discount() {
        item = new Item("ITEM000002", "苹果", "斤", 5.50);
        item.setHasDiscount(true);
        purchasedItem = new PurchasedItem(item);

        purchasedItem.setCount(2);

        assertThat(purchasedItem.toString(), is("名称：苹果，数量：2斤，单价：5.50(元)，小计：10.45(元)，节省0.55(元)"));
    }
}
