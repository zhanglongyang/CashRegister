package com.thoughtworks.models;

/**
 * Created by lyzhang on 2/25/16.
 */
public class Item {

    private String barCode;
    private String name;
    private String unit;
    private Double price;

    private Boolean hasGift;
    private Boolean hasDiscount;

    public Item() {}

    public Item(String barCode, String name, String unit, Double price) {
        setBarCode(barCode);
        setName(name);
        setUnit(unit);
        setPrice(price);
    }

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

    public void setPrice(String price) {
        this.price = Double.valueOf(price);
    }

    public void setHasGift(String hasGift) {
        this.hasGift = hasGift != null && hasGift.equals("true") ? true : false;
    }

    public void setHasDiscount(String hasDiscount) {
        this.hasDiscount = hasDiscount != null && hasDiscount.equals("true") ? true : false;
    }
}
