package com.thoughtworks;

import com.thoughtworks.models.LineItem;
import com.thoughtworks.models.Shop;
import com.thoughtworks.utils.ItemsLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "[\"ITEM000001\", \"ITEM000001\", \"ITEM000001\", \"ITEM000002-5\", \"ITEM000003\", \"ITEM000003\"]";

        List<LineItem> lineItems = new ItemsLoader().load(input, "items.xml");
        Receipt receipt = new Receipt(new Shop("没钱赚商店"));
        receipt.setItems(lineItems);

        System.out.println(receipt.info());
    }
}
