package com.test;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShopTest {

    @Test
    public void canInstantiate() {
        Shop shop = new Shop();
        assertNotNull(shop);
    }

    @Test
    public void canGetEmtpyCart() {
        Shop shop = new Shop();
        Cart cart = shop.getNewCart();
        assertNotNull(cart);
        assertThat(cart.isEmpty(), is(true));
    }
}