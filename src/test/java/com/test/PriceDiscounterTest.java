package com.test;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PriceDiscounterTest {

    @Test
    public void canCreate() {
        PriceDiscounter discounter = new PriceDiscounter(Pack::getTotalPrice);
        assertNotNull(discounter);
    }

    @Test(expected = PriceDiscounter.IncorrectPriceException.class)
    public void priceDiscounterCannotIncreasePrice() {
        Discount discount = pack -> pack.getTotalPrice() * 2;
        PriceDiscounter discounter = new PriceDiscounter(discount);
        Pack pack = new Pack(new Item("product", "brand", 1L), 1);
        long totalPrice = pack.getTotalPrice();
        assertThat(totalPrice, is(equalTo(1L)));
        discounter.apply(pack);
    }
}