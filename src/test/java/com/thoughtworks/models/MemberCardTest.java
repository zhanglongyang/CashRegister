package com.thoughtworks.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MemberCardTest {
    @Test
    public void should_have_card_no() {
        MemberCard card = new MemberCard("123456789012");

        assertThat(card.getCardNo(), is("123456789012"));
    }

    @Test
    public void should_be_golden_user_if_card_no_contains_G() {
        MemberCard card = new MemberCard("123456789012G");

        assertThat(card.isGolden(), is(true));
    }

    @Test
    public void should_be_normal_user_if_card_no_not_contain_G() {
        MemberCard card = new MemberCard("123456789012");

        assertThat(card.isGolden(), is(false));
    }

    @Test
    public void should_have_discount_info() {
        MemberCard card = new MemberCard("123456789012", 0.88);

        assertThat(card.getDiscount(), is(0.88));
    }
}
