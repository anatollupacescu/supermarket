package com.test;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void canCreateEmptyCart() {
        Cart cart = new Cart();
        assertNotNull(cart);
    }

    @Test(expected = NullPointerException.class)
    public void canNotAddNullItem() {
        Cart cart = new Cart();
        cart.addItem(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddZeroItems() {
        Cart cart = new Cart();
        cart.addItem(new Item("test", "test", 1L), 0);
    }

    @Test
    public void canAddItemsToCart() {
        Cart cart = new Cart();
        cart.addItem(new Item("shampoo", "brand", 100L), 1);
        assertThat(cart.isEmpty(), is(false));
    }

    @Test
    public void doesNotAcceptDuplicateItems() {
        Cart cart = new Cart();
        cart.addItem(new Item("shampoo", "brand", 100L), 1);
        cart.addItem(new Item("shampoo", "brand", 100L), 2);
        Set<Pack> cartItems = cart.getItems();
        assertThat(cartItems.size(), is(equalTo(1)));
        Pack cartItem = cartItems.iterator().next();
        assertThat(cartItem.getItem().getName(), is(equalTo("shampoo")));
        assertThat(cartItem.getItemCount(), is(equalTo(3)));
    }
}