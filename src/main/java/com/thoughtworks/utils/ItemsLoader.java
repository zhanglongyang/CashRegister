package com.thoughtworks.utils;

import com.thoughtworks.PurchasedItem;
import com.thoughtworks.models.Item;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by lyzhang on 3/2/16.
 */
public class ItemsLoader {
    private static final String INPUT_SEPARATOR = "-";
    private static final String CONFIG_SEPARATOR = ".";
    private static final String PREFIX = "Item";

    public List<PurchasedItem> load(List<String> items, String resource) {
        Map<String, Integer> purchasedItemsWithCount = parseInput(items);

        List<PurchasedItem> purchasedItems = new ArrayList<>();
        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resource), "UTF-8"));

            for (String barCode : purchasedItemsWithCount.keySet()) {
                Item item = new Item();

                for (Field f : Item.class.getDeclaredFields()) {
                    Object o = properties.getProperty(PREFIX + CONFIG_SEPARATOR + barCode + CONFIG_SEPARATOR + f.getName());
                    if (o != null) {
                        Method m = item.getClass().getMethod("set" + capitalize(f.getName()), o.getClass());

                        m.invoke(item, o);
                    }
                }

                PurchasedItem purchasedItem = new PurchasedItem(item);
                purchasedItem.setCount(purchasedItemsWithCount.get(barCode));
                purchasedItems.add(purchasedItem);
            }
        } catch (Exception e) {
            System.out.println("Something wrong when loading items.");
        }

        return purchasedItems;
    }

    private Map<String, Integer> parseInput(List<String> purchasedItems) {
        Map<String, Integer> purchasedItemMap = new HashMap<>();

        for (String item : purchasedItems) {
            String key = item;
            Integer count = 1;
            if (item.contains(INPUT_SEPARATOR)) {
                key = item.split(INPUT_SEPARATOR)[0];
                count = Integer.valueOf(item.split(INPUT_SEPARATOR)[1]);
            }

            if (purchasedItemMap.containsKey(key)) {
                count += purchasedItemMap.get(key);
            }

            purchasedItemMap.put(key, count);
        }

        return purchasedItemMap;
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
