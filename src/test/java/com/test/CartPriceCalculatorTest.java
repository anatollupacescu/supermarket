package com.test;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CartPriceCalculatorTest {

    @Test
    public void canCreate() {
        Cart cart = new Cart();
        CartPriceCalculator calculator = new CartPriceCalculator(cart);
        assertNotNull(calculator);
    }

    @Test
    public void emptyCartTotalPriceIsZero() {
        Cart cart = new Cart();
        CartPriceCalculator calculator = new CartPriceCalculator(cart);
        assertThat(calculator.getPrice(), is(equalTo(0L)));
    }

    @Test
    public void calculatesTotalPriceOnCartWithItems() {
        CartPriceCalculator calculator = createCalculator();
        assertThat(calculator.getPrice(), is(equalTo(50L)));
    }

    @Test(expected = CartIsEmptyException.class)
    public void canNotApplyDiscountOnEmptyCart() {
        Cart cart = new Cart();
        CartPriceCalculator calculator = new CartPriceCalculator(cart);
        calculator.applyPriceDiscount(c -> 0L);
    }

    @Test
    public void canApplyDiscount() {
        CartPriceCalculator calculator = createCalculator();
        final long cartPrice = calculator.getPrice();
        Discount noDiscount = Pack::getItemPrice;
        calculator.applyPriceDiscount(noDiscount);
        assertThat(calculator.getPrice(), is(equalTo(cartPrice)));
    }

    @Test
    public void realDiscountChangesTotalPrice() {
        CartPriceCalculator calculator = createCalculator();
        final long cartPriceBeforeDiscount = calculator.getPrice();
        Discount halfPriceDiscount = pack -> pack.getTotalPrice() / 2;
        calculator.applyPriceDiscount(halfPriceDiscount);
        assertThat(calculator.getPrice() < cartPriceBeforeDiscount, is(true));
    }

    @Test(expected = PriceDiscounter.IncorrectPriceException.class)
    public void canNotApplyBadlyWrittenDiscount() {
        CartPriceCalculator calculator = createCalculator();
        Discount halfPriceDiscount = pack -> pack.getTotalPrice() * 2;
        calculator.applyPriceDiscount(halfPriceDiscount);
    }

    private CartPriceCalculator createCalculator() {
        Cart cart = new Cart();
        Item item1 = new Item("item1", "brand", 10L);
        Item item2 = new Item("item2", "otherBrand", 20L);
        cart.addItem(item1, 1);
        cart.addItem(item2, 2);
        return new CartPriceCalculator(cart);
    }
}
