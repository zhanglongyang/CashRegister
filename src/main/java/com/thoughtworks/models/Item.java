package com.thoughtworks.models;

import com.thoughtworks.discounts.Buy2Give1Discount;
import com.thoughtworks.discounts.Discount;
import com.thoughtworks.discounts.PercentageDiscount;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Item {

    private String barCode;
    private String name;
    private String unit;
    private Double price;

    private List<Discount> discounts;

    public Item() {
        discounts = new ArrayList<>();
    }

    public Item(String barCode, String name, String unit, Double price) {
        this();

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

    public Boolean hasDiscount() {
        for (Discount discount : discounts) {
            if (discount instanceof PercentageDiscount) {
                return true;
            }
        }

        return false;
    }

    public Boolean hasGift() {
        for (Discount discount : discounts) {
            if (discount instanceof Buy2Give1Discount) {
                return true;
            }
        }

        return false;
    }

    @XmlElement
    public void setDiscountConfig(String discountConfig) {
        String [] discountArray = discountConfig.split(",");

        for (String discountStr : discountArray) {
            String className = "com.thoughtworks.discounts." + discountStr;

            try {
                Class clazz = Class.forName(className);

                Discount discountObj = (Discount) clazz.newInstance();

                discounts.add(discountObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }
}
