package com.thoughtworks;

import com.thoughtworks.models.LineItem;
import com.thoughtworks.utils.ItemsLoader;

import java.util.List;

/**
 * Created by lyzhang on 2/29/16.
 */
public class Main {
    public static void main(String[] args) {
        String input = "[\"ITEM000001\", \"ITEM000001\", \"ITEM000001\", \"ITEM000002-5\", \"ITEM000003\", \"ITEM000003\"]";

        List<LineItem> lineItems = new ItemsLoader().load(input, "items.properties");
        Receipt receipt = new Receipt(lineItems);

        System.out.println(receipt.info());
    }
}
