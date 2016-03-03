package com.thoughtworks.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.thoughtworks.models.Item;
import com.thoughtworks.models.LineItem;

import java.io.IOException;
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

    public List<LineItem> load(String input, String resource) {
        Map<String, Integer> purchasedItemsWithCount = parseInput(input);

        List<LineItem> lineItems = new ArrayList<>();
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

                LineItem lineItem = new LineItem(item);
                lineItem.setCount(purchasedItemsWithCount.get(barCode));
                lineItems.add(lineItem);
            }
        } catch (Exception e) {
            System.out.println("Something wrong when loading items.");
        }

        return lineItems;
    }

    private Map<String, Integer> parseInput(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List<String> items = new ArrayList<>();

        try {
            items = mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));
        } catch (IOException e) {
            System.out.println("Parsing input json error: " + e.getMessage());
        }

        Map<String, Integer> purchasedItemMap = new HashMap<>();

        for (String item : items) {
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
