package com.thoughtworks;

import com.thoughtworks.models.LineItem;
import com.thoughtworks.utils.PriceFormatter;

import java.util.List;

public class Receipt extends Template {
    private List<LineItem> items;

    public Receipt() {}

    public Receipt(List<LineItem> items) {
        this.items = items;
    }

    public String lineItemsSection() {
        StringBuilder sb = new StringBuilder();

        for (LineItem item : getItems()) {
            sb.append(item.toString()).append("\n");
        }

        sb.append(delimiter());

        return sb.toString();
    }

    public String giftItemsSection() {
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

        sb.insert(0, "买二赠一商品：\n");
        sb.append(delimiter());

        return sb.toString();
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


    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public List<LineItem> getItems() {
        return items;
    }
}
