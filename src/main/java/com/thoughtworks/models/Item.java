package com.thoughtworks.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lyzhang on 2/25/16.
 */
@XmlRootElement
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

    @XmlElement
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    @XmlElement
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean hasGift() {
        if (hasGift == null) {
            hasGift = false;
        }

        return hasGift;
    }

    @XmlElement
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

    @XmlElement
    public void setHasGift(String hasGift) {
        this.hasGift = hasGift != null && hasGift.equals("true") ? true : false;
    }

    @XmlElement
    public void setHasDiscount(String hasDiscount) {
        this.hasDiscount = hasDiscount != null && hasDiscount.equals("true") ? true : false;
    }
}
