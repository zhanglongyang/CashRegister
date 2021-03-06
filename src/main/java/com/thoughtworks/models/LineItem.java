package com.thoughtworks.models;

import com.thoughtworks.discounts.Discount;
import com.thoughtworks.utils.PriceFormatter;

public class LineItem {
    private Item item;
    private Integer count;

    public LineItem(Item item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double calculateSubtotal() {
        for (Discount discount : item.getDiscounts()) {
            return discount.subtotal(this);
        }

        return item.getPrice() * getCount();
    }

    public Double calculateSaving() {
        return item.getPrice() * getCount() - calculateSubtotal();
    }

    public String getName() {
        return item.getName();
    }

    public String getUnit() {
        return item.getUnit();
    }

    public boolean hasGift() {
        return item.hasGift();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        PriceFormatter formatter = new PriceFormatter();

        sb.append("名称：").append(getName()).append("，");
        sb.append("数量：").append(getCount()).append(getUnit()).append("，");
        sb.append("单价：").append(formatter.format(item.getPrice())).append("(元)").append("，");
        sb.append("小计：").append(formatter.format(calculateSubtotal())).append("(元)");

        if (item.hasDiscount()) {
            sb.append("，");
            sb.append("节省").append(formatter.format(calculateSaving())).append("(元)");
        }

        return sb.toString();
    }

    public double getPrice() {
        return item.getPrice();
    }
}
