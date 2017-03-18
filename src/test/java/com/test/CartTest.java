package com.test;

import com.test.item.Shampoo;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void canCreateEmptyCart() {
        Cart cart = new Cart();
        assertNotNull(cart);
    }

    @Test
    public void canAddItemsToCart() {
        Cart cart = new Cart();
        cart.addItem(new Shampoo("brand", 100L), 1);
        assertThat(cart.isEmpty(), is(false));
    }
}