package com.thoughtworks.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.thoughtworks.models.Item;
import com.thoughtworks.models.Items;
import com.thoughtworks.models.LineItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsLoader {
    private static final String INPUT_SEPARATOR = "-";

    public List<LineItem> load(String input, String resource) {

        List<Item> itemList = parseItems(resource);

        List<LineItem> lineItems = new ArrayList<>();
        Map<String, Integer> purchasedItemsWithCount = parseInput(input);
        try {
            for (String barCode : purchasedItemsWithCount.keySet()) {
                Item item = new Item();

                for (Item i : itemList) {
                    if (barCode.equals(i.getBarCode())) {
                        item = i;
                        break;
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

    private List<Item> parseItems(String resource) {
        List<Item> itemList = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Items items = (Items) jaxbUnmarshaller.unmarshal(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resource), "UTF-8"));

            itemList = items.all();
        } catch (Exception e) {
            System.out.println("Paring items failed with error: " + e.getMessage());
        }

        return itemList;
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
}
