package com.thoughtworks;

import java.text.DecimalFormat;
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

    public String printHeader() {
        return "***<没钱赚商店>购物清单***";
    }

    public String printItemList() {
        StringBuilder sb = new StringBuilder();

        for (Item item : getItems()) {
            sb.append(item.toString());
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public String printGiftItemList() {
        if (getItems().isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("买二赠一商品：\n");

        for (Item item : getItems()) {
            if (item.hasGift()) {
                sb.append("名称：").append(item.getName()).append("，");
                sb.append("数量：").append(1).append(item.getUnit());
            }
        }

        return sb.toString();
    }

    public String printTotalPrice() {
        Double totalPrice = 0.0;

        for (Item item : getItems()) {
            totalPrice += item.calculateSubtotal();
        }

        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");

        if (totalPrice > 0.0) {
            sb.append("总计：").append(df.format(totalPrice)).append("(元)");
        }

        return sb.toString();
    }

    public String printTotalCutDown() {
        Double totalCutDown = 0.0;

        for (Item item : getItems()) {
            totalCutDown += item.calculateCutDown();
        }

        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");

        if (totalCutDown > 0.0) {
            sb.append("节省：").append(df.format(totalCutDown)).append("(元)");
        }

        return sb.toString();
    }
}
