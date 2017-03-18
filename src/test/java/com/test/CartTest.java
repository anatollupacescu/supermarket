package com.test;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void canCreateEmptyCart() {
        Cart cart = new Cart();
        assertNotNull(cart);
        assertThat(cart.getTotalPrice(), is(equalTo(0L)));
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
        assertThat(cart.getTotalPrice(), is(equalTo(100L)));
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

    @Test
    public void canAddSameProductIfBrandDiffers() {
        Cart cart = new Cart();
        cart.addItem(new Item("shampoo", "one", 100L), 1);
        cart.addItem(new Item("shampoo", "two", 100L), 2);
        Set<Pack> cartItems = cart.getItems();
        assertThat(cartItems.size(), is(equalTo(2)));
    }

    @Test(expected = Cart.CartIsEmptyException.class)
    public void canNotApplyDiscountOnEmptyCart() {
        Cart cart = new Cart();
        cart.applyPriceDiscount(c -> 0L);
    }

    @Test
    public void canApplyDiscount() {
        Cart cart = new Cart();
        cart.addItem(new Item("shampoo", "one", 100L), 1);
        final long cartPrice = cart.getTotalPrice();
        Discount noDiscount = p -> p.getTotalPrice();
        Cart discountedCart = cart.applyPriceDiscount(noDiscount);
        assertThat(discountedCart, is(notNullValue()));
        assertThat(discountedCart, is(not(sameInstance(cart))));
        assertThat(discountedCart.getTotalPrice(), is(equalTo(cartPrice)));
    }

    @Test
    public void realDiscountChangesTotalPrice() {
        Cart cart = new Cart();
        Item shampoo = new Item("shampoo", "one", 100L);
        cart.addItem(shampoo, 1);
        final long cartPrice = cart.getTotalPrice();
        Discount halfPriceDiscount = pack -> pack.getTotalPrice() / 2;
        Cart discountedCart = cart.applyPriceDiscount(halfPriceDiscount);
        assertThat(discountedCart, is(notNullValue()));
        assertThat(discountedCart, is(not(sameInstance(cart))));
        assertThat(discountedCart.getTotalPrice() < cartPrice , is(true));
    }

    @Test(expected = PriceDiscounter.IncorrectPriceException.class)
    public void canNotApplyBadlyWrittenDiscount() {
        Cart cart = new Cart();
        Item shampoo = new Item("shampoo", "one", 100L);
        cart.addItem(shampoo, 1);
        Discount halfPriceDiscount = pack -> pack.getTotalPrice() * 2;
        cart.applyPriceDiscount(halfPriceDiscount);
    }
}