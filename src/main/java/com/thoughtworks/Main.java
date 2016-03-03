package com.thoughtworks;

import com.thoughtworks.utils.ItemsLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyzhang on 2/29/16.
 */
public class Main {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("ITEM000001");
        items.add("ITEM000001");
        items.add("ITEM000001");
        items.add("ITEM000002-5");
        items.add("ITEM000003");
        items.add("ITEM000003");

        Receipt receipt = new Receipt();
        List<PurchasedItem> purchasedItems = new ItemsLoader().load(items, "items.properties");
        receipt.setItems(purchasedItems);

        System.out.println(receipt.info());
    }
}
