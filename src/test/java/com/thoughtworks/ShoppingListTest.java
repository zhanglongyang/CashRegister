package com.thoughtworks;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lyzhang on 2/28/16.
 */
public class ShoppingListTest {
    ShoppingList shoppingList;
    List<Item> items;

    @Before
    public void setup() {
        shoppingList = new ShoppingList();

        items = new ArrayList<>();
        Item cocaCola = new Item();
        cocaCola.setName("可口可乐");
        cocaCola.setCount(3);
        cocaCola.setUnit("瓶");
        cocaCola.setPrice(3.00);
        cocaCola.setHasGift(true);
        items.add(cocaCola);

        Item apple = new Item();
        apple.setName("苹果");
        apple.setCount(2);
        apple.setPrice(5.50);
        apple.setUnit("斤");
        items.add(apple);

        shoppingList.setItems(items);
    }

    @Test
    public void should_have_items() {
        assertThat(shoppingList.getItems(), is(items));
    }

    @Test
    public void should_print_header() {
        assertThat(shoppingList.header(), is("***<没钱赚商店>购物清单***\n"));
    }

    @Test
    public void should_print_item_list() {
        assertThat(shoppingList.itemList(), is("名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n名称：苹果，数量：2斤，单价：5.50(元)，小计：11.00(元)\n"));
    }

    @Test
    public void should_print_gift_item_list() {
        assertThat(shoppingList.giftItemList(), is("买二赠一商品：\n名称：可口可乐，数量：1瓶\n"));
    }

    @Test
    public void should_print_total_price() {
        assertThat(shoppingList.totalPrice(), is("总计：17.00(元)\n"));
    }

    @Test
    public void should_print_total_cut_down() {
        assertThat(shoppingList.totalCutDown(), is("节省：3.00(元)\n"));
    }

    @Test
    public void should_print_all_information() {
        assertThat(shoppingList.allInformation(),
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
