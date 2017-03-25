package com.test.example;

import com.test.supermarket.CartItems;
import com.test.supermarket.Discount;

public class BuyTwoGetOneFreeDiscount implements Discount {

    @Override
    public boolean isApplicable(CartItems packs) {
        return packs.stream().anyMatch(pack -> pack.getItemCount() > 2);
    }

    @Override
    public CartItems apply(CartItems packs) {
        final CartItems discountedCartItems = new CartItems();
        packs.stream().map(pack -> {
            if(pack.getItemCount() < 3) return pack;
            int discounts = pack.getItemCount() / 3;
            return pack.copyWithDiscount(pack.getItem().getPrice() * discounts);
        }).forEach(discountedCartItems::add);
        return discountedCartItems;
    }
}
