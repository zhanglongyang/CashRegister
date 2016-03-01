package com.thoughtworks;

import com.thoughtworks.util.PriceFormatter;

import java.util.List;

/**
 * Created by lyzhang on 2/28/16.
 */
public class ShoppingList {
    private List<Item> items;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public String header() {
        return "***<没钱赚商店>购物清单***\n";
    }

    public String delimiter() {
        return "----------------------\n";
    }

    public String footer() {
        return "**********************";
    }

    public String itemList() {
        StringBuilder sb = new StringBuilder();

        for (Item item : getItems()) {
            sb.append(item.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public String giftItemList() {
        if (getItems().isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (Item item : getItems()) {
            if (item.hasGift()) {
                sb.append("名称：").append(item.getName()).append("，");
                sb.append("数量：").append(1).append(item.getUnit());
                sb.append("\n");
            }
        }

        if (sb.length() == 0) {
            return "";
        }

        return delimiter() + "买二赠一商品：\n" + sb.toString();
    }

    public String totalPrice() {
        Double totalPrice = 0.0;

        for (Item item : getItems()) {
            totalPrice += item.calculateSubtotal();
        }

        StringBuilder sb = new StringBuilder();

        if (totalPrice > 0.0) {
            sb.append("总计：").append(new PriceFormatter("0.00").format(totalPrice)).append("(元)\n");
        }

        return sb.toString();
    }

    public String totalCutDown() {
        Double totalCutDown = 0.0;

        for (Item item : getItems()) {
            totalCutDown += item.calculateCutDown();
        }

        StringBuilder sb = new StringBuilder();

        if (totalCutDown > 0.0) {
            sb.append("节省：").append(new PriceFormatter("0.00").format(totalCutDown)).append("(元)\n");
        }

        return sb.toString();
    }

    public String allInformation() {
        StringBuilder sb = new StringBuilder();

        sb.append(header());
        sb.append(itemList());
        sb.append(giftItemList());
        sb.append(delimiter());
        sb.append(totalPrice());
        sb.append(totalCutDown());
        sb.append(footer());

        return sb.toString();
    }
}
