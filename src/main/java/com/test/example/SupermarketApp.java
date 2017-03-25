package com.test.example;

import com.test.supermarket.*;

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
        log.info("Cart price before discount: " + discountedCart.preview().getTotalPrice());
        log.info("Cart before discount: " + discountedCart.preview());
        log.info("-------------");
        discountedCart.addDiscount(new BuyTwoGetOneFreeDiscount());
        CartItems cartItemsPreview = discountedCart.preview();
        log.info("Price preview: " + cartItemsPreview.getTotalPrice());
        log.info("Cart preview: " + cartItemsPreview);
        log.info("-------------");
        discountedCart.apply();
        log.info("Cart after discount: " + discountedCart.getItems());
        log.info("Cart price after discount: " + discountedCart.getItems().getTotalPrice());
    }
}
