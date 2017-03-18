package com.test;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PriceDiscounterTest {

    @Test
    public void canCreate() {
        PriceDiscounter discounter = new PriceDiscounter(Pack::getTotalPrice);
        assertNotNull(discounter);
    }

    @Test
    public void canApplyDiscountOnSingleItemCart() {
        PriceDiscounter discounter = new PriceDiscounter(Pack::getItemPrice);
        Pack pack = new Pack(new Item("product", "brand", 1L), 1);
        Pack discountedPack = discounter.apply(pack);
        assertThat(pack, is(equalTo(discountedPack)));
    }

    @Test
    public void canApplyDiscountOnMultiItemCart() {
        PriceDiscounter discounter = new PriceDiscounter(Pack::getItemPrice);
        Item item = new Item("product1", "brand", 10L);
        Pack pack = new Pack(item, 2);
        Pack discountedPack = discounter.apply(pack);
        assertThat(pack.getTotalPrice(), is(equalTo(discountedPack.getTotalPrice())));
    }

    @Test
    public void discountUpdatesItemPrice() {
        PriceDiscounter discounter = new PriceDiscounter(pack -> pack.getItemPrice() / 2);
        Item item = new Item("product1", "brand", 10L);
        Pack pack = new Pack(item, 2);
        Pack discountedPack = discounter.apply(pack);
        assertThat(pack.getTotalPrice(), is(equalTo(2 * discountedPack.getTotalPrice())));
    }

    @Test(expected = PriceDiscounter.IncorrectPriceException.class)
    public void priceDiscounterCannotIncreasePrice() {
        Discount discount = pack -> pack.getItemPrice() * 2;
        PriceDiscounter discounter = new PriceDiscounter(discount);
        Pack pack = new Pack(new Item("product", "brand", 1L), 1);
        long totalPrice = pack.getTotalPrice();
        assertThat(totalPrice, is(equalTo(1L)));
        discounter.apply(pack);
    }
}