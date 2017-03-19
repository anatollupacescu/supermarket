package com.test;

import com.test.supermarket.PackPriceDiscounter;
import com.test.supermarket.domain.Item;
import com.test.supermarket.domain.Pack;
import com.test.supermarket.exception.IncorrectPriceException;
import org.junit.Test;

import static com.test.Discounts.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PackPriceDiscounterTest {

    @Test
    public void canCreate() {
        PackPriceDiscounter discounter = new PackPriceDiscounter(noDiscount);
        assertNotNull(discounter);
    }

    @Test
    public void canApplyDiscountOnSingleItemCart() {
        PackPriceDiscounter discounter = new PackPriceDiscounter(halfPriceDiscount);
        Pack pack = new Pack(new Item("product", "brand", 10L), 1);
        Pack discountedPack = discounter.apply(pack);
        assertThat(discountedPack.getTotalPrice(), is(equalTo(5L)));
    }

    @Test
    public void canApplyDiscountOnMultiItemCart() {
        PackPriceDiscounter discounter = new PackPriceDiscounter(halfPriceDiscount);
        Item item = new Item("product1", "brand", 10L);
        Pack pack = new Pack(item, 2);
        Pack discountedPack = discounter.apply(pack);
        assertThat(discountedPack.getTotalPrice(), is(equalTo(10L)));
    }

    @Test
    public void discountUpdatesItemPrice() {
        PackPriceDiscounter discounter = new PackPriceDiscounter(halfPriceDiscount);
        Item item = new Item("product1", "brand", 10L);
        Pack pack = new Pack(item, 2);
        Pack discountedPack = discounter.apply(pack);
        assertThat(discountedPack.getTotalPrice(), is(equalTo(10L)));
    }

    @Test(expected = IncorrectPriceException.class)
    public void priceDiscounterCannotIncreasePrice() {
        PackPriceDiscounter discounter = new PackPriceDiscounter(incorrectDiscount);
        Pack pack = new Pack(new Item("product", "brand", 1L), 1);
        long totalPrice = pack.getTotalPrice();
        assertThat(totalPrice, is(equalTo(1L)));
        discounter.apply(pack);
    }
}