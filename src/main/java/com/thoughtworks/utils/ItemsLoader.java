package com.thoughtworks.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.thoughtworks.models.Item;
import com.thoughtworks.models.Items;
import com.thoughtworks.models.LineItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by lyzhang on 3/2/16.
 */
public class ItemsLoader {
    private static final String INPUT_SEPARATOR = "-";

    public List<LineItem> load(String input, String resource) {
        Map<String, Integer> purchasedItemsWithCount = parseInput(input);
        List<LineItem> lineItems = new ArrayList<>();

        List<Item> allItems = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Items itemObjects = (Items) jaxbUnmarshaller.unmarshal(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resource), "UTF-8"));

            allItems = itemObjects.all();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resource), "UTF-8"));

            for (String barCode : purchasedItemsWithCount.keySet()) {
                Item item = new Item();

                for (Item i : allItems) {
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
