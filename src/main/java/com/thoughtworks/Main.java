package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyzhang on 2/29/16.
 */
public class Main {
    public static void main(String[] args) {
        printWithGiftItems();

        printWithoutGiftItems();

        printWithDiscountItems();

        printWithBothGiftAndDiscountItems();
    }

    private static void printWithGiftItems() {
        List<Item> items = new ArrayList<>();

        Item cocaCola = new Item();
        cocaCola.setName("可口可乐");
        cocaCola.setCount(3);
        cocaCola.setUnit("瓶");
        cocaCola.setPrice(3.00);
        cocaCola.setHasGift(true);
        items.add(cocaCola);

        Item badminton = new Item();
        badminton.setName("羽毛球");
        badminton.setCount(5);
        badminton.setUnit("个");
        badminton.setPrice(1.00);
        badminton.setHasGift(true);
        items.add(badminton);

        Item apple = new Item();
        apple.setName("苹果");
        apple.setCount(2);
        apple.setUnit("斤");
        apple.setPrice(5.50);
        items.add(apple);

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setItems(items);

        System.out.println(shoppingList.allInformation());
        System.out.println();
    }

    private static void printWithoutGiftItems() {
        List<Item> items = new ArrayList<>();

        Item cocaCola = new Item();
        cocaCola.setName("可口可乐");
        cocaCola.setCount(3);
        cocaCola.setUnit("瓶");
        cocaCola.setPrice(3.00);
        items.add(cocaCola);

        Item badminton = new Item();
        badminton.setName("羽毛球");
        badminton.setCount(5);
        badminton.setUnit("个");
        badminton.setPrice(1.00);
        items.add(badminton);

        Item apple = new Item();
        apple.setName("苹果");
        apple.setCount(2);
        apple.setUnit("斤");
        apple.setPrice(5.50);
        items.add(apple);

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setItems(items);

        System.out.println(shoppingList.allInformation());
        System.out.println();
    }

    private static void printWithDiscountItems() {
        List<Item> items = new ArrayList<>();

        Item cocaCola = new Item();
        cocaCola.setName("可口可乐");
        cocaCola.setCount(3);
        cocaCola.setUnit("瓶");
        cocaCola.setPrice(3.00);
        items.add(cocaCola);

        Item badminton = new Item();
        badminton.setName("羽毛球");
        badminton.setCount(5);
        badminton.setUnit("个");
        badminton.setPrice(1.00);
        items.add(badminton);

        Item apple = new Item();
        apple.setName("苹果");
        apple.setCount(2);
        apple.setUnit("斤");
        apple.setPrice(5.50);
        apple.setHasDiscount(true);
        items.add(apple);

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setItems(items);

        System.out.println(shoppingList.allInformation());
        System.out.println();
    }

    private static void printWithBothGiftAndDiscountItems() {
        List<Item> items = new ArrayList<>();

        Item cocaCola = new Item();
        cocaCola.setName("可口可乐");
        cocaCola.setCount(3);
        cocaCola.setUnit("瓶");
        cocaCola.setPrice(3.00);
        cocaCola.setHasGift(true);
        items.add(cocaCola);

        Item badminton = new Item();
        badminton.setName("羽毛球");
        badminton.setCount(5);
        badminton.setUnit("个");
        badminton.setPrice(1.00);
        badminton.setHasGift(true);
        items.add(badminton);

        Item apple = new Item();
        apple.setName("苹果");
        apple.setCount(2);
        apple.setUnit("斤");
        apple.setPrice(5.50);
        apple.setHasDiscount(true);
        items.add(apple);

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setItems(items);

        System.out.println(shoppingList.allInformation());
        System.out.println();
    }
}
