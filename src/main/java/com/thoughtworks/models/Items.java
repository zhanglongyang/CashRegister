package com.thoughtworks.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyzhang on 3/3/16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Items {
    @XmlElement(name = "item")
    List<Item> items = new ArrayList<>();

    public List<Item> all() {
        return items;
    }
}
