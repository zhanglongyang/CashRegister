package com.thoughtworks.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShopTest {
    @Test
    public void shop_should_have_name() {
        Shop shop = new Shop();
        shop.setName("没钱赚商店");

        assertThat(shop.getName(), is("没钱赚商店"));
    }
}
