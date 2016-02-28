package com.thoughtworks;

import java.util.List;

/**
 * Created by lyzhang on 2/28/16.
 */
public class ShoppingList {
    private List<Item> items;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public String printHeader() {
        return "***<没钱赚商店>购物清单***";
    }

    public String printItemList() {
        StringBuilder sb = new StringBuilder();

        for (Item item : getItems()) {
            sb.append(item.toString());
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
