package com.test.example;

import com.test.supermarket.CartPriceCalculator;
import com.test.supermarket.PackPriceReviser;
import com.test.supermarket.domain.Cart;
import com.test.supermarket.domain.Item;

public class SupermarketApp {

    private SupermarketApp() {
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        Item phone = new Item("phone", "lg", 100L);
        Item smartWatch = new Item("smartWatch", "lg", 50L);
        cart.addItem(phone, 1);
        cart.addItem(smartWatch, 1);
        CartPriceCalculator calculator = new CartPriceCalculator(cart);
        System.out.println("Price before discount: " + calculator.getPrice());
        PackPriceReviser twoItemsFromTheSameBrandGet10PercentDiscount = new SampleDiscount();
        PackPriceDiscounter discounter = new PackPriceDiscounter(twoItemsFromTheSameBrandGet10PercentDiscount);
        calculator.useDiscounter(discounter);
        System.out.println("Price after discount: " + calculator.getPrice());
    }
}
