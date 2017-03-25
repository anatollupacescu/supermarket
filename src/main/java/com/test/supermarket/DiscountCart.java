package com.test.supermarket;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DiscountCart {

    private Set<Discount> discounts;

    private CartItems items;

    public DiscountCart(Cart cart) {
        this.items = cart.getItems();
        this.discounts = new HashSet<>();
    }

    public void addDiscount(Discount discount) {
        Preconditions.checkNotNull(discount);
        discounts.add(discount);
    }

    public CartItems preview() {
        CartItems previewItems = new CartItems(items);
        for(Discount discount : discounts) {
            if(discount.isApplicable(previewItems)) {
                previewItems = discount.apply(previewItems);
            }
        }
        return previewItems;
    }

    public void apply() {
        this.items = preview();
        this.discounts = Collections.emptySet();
    }

    public CartItems getItems() {
        return new CartItems(items);
    }
}
