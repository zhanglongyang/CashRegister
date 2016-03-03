package com.thoughtworks;

import com.thoughtworks.models.LineItem;
import com.thoughtworks.utils.PriceFormatter;

import java.util.List;

/**
 * Created by lyzhang on 2/28/16.
 */
public class Receipt {
    private List<LineItem> items;

    public Receipt(){}

    public Receipt(List<LineItem> items) {
        this.items = items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public List<LineItem> getItems() {
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

        for (LineItem item : getItems()) {
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

        for (LineItem item : getItems()) {
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

        for (LineItem item : getItems()) {
            totalPrice += item.calculateSubtotal();
        }

        StringBuilder sb = new StringBuilder();

        if (totalPrice > 0.0) {
            sb.append("总计：").append(new PriceFormatter().format(totalPrice)).append("(元)\n");
        }

        return sb.toString();
    }

    public String totalSaving() {
        Double totalSaving = 0.0;

        for (LineItem item : getItems()) {
            totalSaving += item.calculateSaving();
        }

        StringBuilder sb = new StringBuilder();

        if (totalSaving > 0.0) {
            sb.append("节省：").append(new PriceFormatter().format(totalSaving)).append("(元)\n");
        }

        return sb.toString();
    }

    public String info() {
        StringBuilder sb = new StringBuilder();

        sb.append(header());
        sb.append(itemList());
        sb.append(giftItemList());
        sb.append(delimiter());
        sb.append(totalPrice());
        sb.append(totalSaving());
        sb.append(footer());

        return sb.toString();
    }
}
