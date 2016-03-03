package com.thoughtworks;

import com.thoughtworks.models.LineItem;
import com.thoughtworks.receipt.Receipt;
import com.thoughtworks.utils.ItemsLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Sample#1
        String purchasedList = "[\"ITEM000001\", \"ITEM000001\", \"ITEM000001\", \"ITEM000002-5\", \"ITEM000003\", \"ITEM000003\"]";
        String input = "items-1.xml";
        printReceipt(purchasedList, input);

        // Sample#2
        input = "items-2.xml";
        printReceipt(purchasedList, input);

        // Sample#3
        input = "items-3.xml";
        printReceipt(purchasedList, input);

        // Sample#4
        purchasedList = "[\"ITEM000001\", \"ITEM000001\", \"ITEM000001\", \"ITEM000002-6\", \"ITEM000003\", \"ITEM000003\"]";
        input = "items-4.xml";
        printReceipt(purchasedList, input);
    }

    private static void printReceipt(String purchasedList, String input) {
        List<LineItem> lineItems = new ItemsLoader().load(purchasedList, input);
        Receipt receipt = new Receipt(lineItems);

        System.out.println(receipt.info());
        System.out.println();
    }
}
