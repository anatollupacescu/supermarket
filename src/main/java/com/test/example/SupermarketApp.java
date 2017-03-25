package com.test.example;

import com.test.supermarket.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SupermarketApp {

    private static final Logger log = Logger.getLogger("supermarket");

    private SupermarketApp() {
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        Item phone = new Item("milk", "lacta", 10L);
        cart.addItem(phone, 3);
        DiscountCart discountedCart = new DiscountCart(cart);
        log.log(Level.INFO, "Cart price before discount: {0}", discountedCart.preview().getTotalPrice());
        log.log(Level.INFO, "Cart before discount: {0}", discountedCart.preview());
        log.log(Level.INFO, "-------------");
        discountedCart.addDiscount(new BuyTwoGetOneFreeDiscount());
        CartItems cartItemsPreview = discountedCart.preview();
        log.log(Level.INFO, "Price preview: {0}", cartItemsPreview.getTotalPrice());
        log.log(Level.INFO, "Cart preview: {0}", cartItemsPreview);
        log.log(Level.INFO, "-------------");
        discountedCart.apply();
        log.log(Level.INFO, "Cart after discount: {0}", discountedCart.getItems());
        log.log(Level.INFO, "Cart price after discount: {0}", discountedCart.getItems().getTotalPrice());
    }
}
