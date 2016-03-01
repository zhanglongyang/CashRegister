package com.thoughtworks;

import java.text.DecimalFormat;

/**
 * Created by lyzhang on 2/25/16.
 */
public class Item {
    private static final Integer DISCOUNT = 95;

    private String barCode;
    private String name;
    private String unit;
    private Double price;
    private Integer count;
    private Boolean hasGift;
    private Boolean hasDiscount;

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean hasGift() {
        if (hasGift == null) {
            hasGift = false;
        }

        return hasGift;
    }

    public void setHasGift(Boolean hasGift) {
        this.hasGift = hasGift;
    }

    public Boolean hasDiscount() {
        if (hasDiscount == null) {
            hasDiscount = false;
        }

        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Double calculateSubtotal() {
        if (hasGift()) {
            return (getCount() - getCount() / 3) * getPrice();
        }

        if (hasDiscount()) {
            return getPrice() * getCount() * DISCOUNT / 100;
        }

        return getPrice() * getCount();
    }

    public Double calculateCutDown() {
        return getPrice() * getCount() - calculateSubtotal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        PriceFormatter formatter = new PriceFormatter("0.00");

        sb.append("名称：").append(getName()).append("，");
        sb.append("数量：").append(getCount()).append(getUnit()).append("，");
        sb.append("单价：").append(formatter.format(getPrice())).append("(元)").append("，");
        sb.append("小计：").append(formatter.format(calculateSubtotal())).append("(元)");

        if(hasDiscount()) {
            sb.append("，");
            sb.append("节省").append(formatter.format(calculateCutDown())).append("(元)");
        }

        return sb.toString();
    }

}
