package com.thoughtworks.models;

import com.thoughtworks.PurchasedItem;
import com.thoughtworks.Receipt;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lyzhang on 2/28/16.
 */
public class ReceiptTest {
    Receipt receipt;
    List<PurchasedItem> items;
    private Item item;

    @Before
    public void setup() {
        receipt = new Receipt();

        items = new ArrayList<>();
        item = new Item("ITEM000001", "可口可乐", "瓶", 3.00);
        item.setHasGift(true);
        PurchasedItem cocaCola = new PurchasedItem(item);
        cocaCola.setCount(3);
        items.add(cocaCola);

        PurchasedItem apple = new PurchasedItem(new Item("ITEM000002", "苹果", "斤", 5.50));
        apple.setCount(2);
        items.add(apple);

        receipt.setItems(items);
    }

    @Test
    public void should_have_items() {
        assertThat(receipt.getItems(), is(items));
    }

    @Test
    public void should_print_header() {
        assertThat(receipt.header(), is("***<没钱赚商店>购物清单***\n"));
    }

    @Test
    public void should_print_item_list() {
        assertThat(receipt.itemList(), is("名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n名称：苹果，数量：2斤，单价：5.50(元)，小计：11.00(元)\n"));
    }

    @Test
    public void should_print_gift_item_list() {
        assertThat(receipt.giftItemList(), is("----------------------\n买二赠一商品：\n名称：可口可乐，数量：1瓶\n"));
    }

    @Test
    public void should_print_total_price() {
        assertThat(receipt.totalPrice(), is("总计：17.00(元)\n"));
    }

    @Test
    public void should_print_total_cut_down() {
        assertThat(receipt.totalSaving(), is("节省：3.00(元)\n"));
    }

    @Test
    public void should_print_all_information() {
        assertThat(receipt.info(),
                is("" +
                        "***<没钱赚商店>购物清单***\n" +
                        "名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n" +
                        "名称：苹果，数量：2斤，单价：5.50(元)，小计：11.00(元)\n" +
                        "----------------------\n" +
                        "买二赠一商品：\n" +
                        "名称：可口可乐，数量：1瓶\n" +
                        "----------------------\n" +
                        "总计：17.00(元)\n" +
                        "节省：3.00(元)\n" +
                        "**********************"));
    }
}