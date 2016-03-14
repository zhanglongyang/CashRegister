package com.thoughtworks;

import com.thoughtworks.models.LineItem;
import com.thoughtworks.models.MemberCard;
import com.thoughtworks.views.Receipt;
import com.thoughtworks.utils.ItemsLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Sample#1
        String purchasedList = "[\"ITEM000001\", \"ITEM000001\", \"ITEM000001\", \"ITEM000002-5\", \"ITEM000003\", \"ITEM000003\"]";
        String input = "items-1.xml";
        MemberCard card = new MemberCard("123456789012", 0.95);
        printReceipt(purchasedList, input, card);

        // Sample#2
        input = "items-2.xml";
        printReceipt(purchasedList, input, card);

        // Sample#3
        input = "items-3.xml";
        printReceipt(purchasedList, input, card);

        // Sample#4
        purchasedList = "[\"ITEM000001\", \"ITEM000001\", \"ITEM000001\", \"ITEM000002-6\", \"ITEM000003\", \"ITEM000003\"]";
        input = "items-4.xml";
        printReceipt(purchasedList, input, card);
    }

    private static void printReceipt(String purchasedList, String input, MemberCard card) {
        List<LineItem> lineItems = new ItemsLoader().load(purchasedList, input);
        Receipt receipt = new Receipt(lineItems, card);

        System.out.println(receipt.info());
        System.out.println();
    }
}
