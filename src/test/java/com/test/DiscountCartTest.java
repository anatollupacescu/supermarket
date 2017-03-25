package com.test;

import com.test.example.BuyTwoGetOneFreeDiscount;
import com.test.supermarket.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DiscountCartTest {

    private Discount everySecondItemOfTheSameBrandGetsHalfPrice = new BuyTwoGetOneFreeDiscount();

    @Test
    public void canCreate() {
        Cart cart = createCart();
        DiscountCart discountCart = new DiscountCart(cart);
        assertNotNull(discountCart);
    }

    @Test
    public void canAddDiscount() {
        Cart cart = createCart();
        DiscountCart discountCart = new DiscountCart(cart);
        assertThat(discountCart.getItems(), is(equalTo(cart.getItems())));
        assertThat(discountCart.preview().getTotalPrice(), is(equalTo(170L)));
        discountCart.addDiscount(everySecondItemOfTheSameBrandGetsHalfPrice);
        CartItems preview = discountCart.preview();
        assertNotNull(preview);
        assertThat(preview.getTotalPrice(), is(equalTo(120L)));
    }

    @Test
    public void discountsCanBeAppliedOnlyOnce() {
        Cart cart = createCart();
        DiscountCart discountCart = new DiscountCart(cart);
        discountCart.addDiscount(everySecondItemOfTheSameBrandGetsHalfPrice);
        discountCart.apply();
        assertThat(discountCart.getItems().getTotalPrice(), is(equalTo(120L)));
        discountCart.apply();
        assertThat(discountCart.getItems().getTotalPrice(), is(equalTo(120L)));
    }

    private Cart createCart() {
        Cart cart = new Cart();
        cart.addItem(new Item("soap", "brand", 20L), 1);
        cart.addItem(new Item("shampoo", "brand", 50L), 3);
        return cart;
    }
}