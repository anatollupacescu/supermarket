package com.test;

import com.google.common.base.Preconditions;

import java.util.Set;
import java.util.stream.Collectors;

public class CartPriceCalculator {

    private Set<Pack> items;

    public CartPriceCalculator(Cart cart) {
        this.items = cart.getItems();
    }

    public long getPrice() {
        return items.stream().mapToLong(Pack::getTotalPrice).sum();
    }

    public void applyPriceDiscount(Discount discount) {
        Preconditions.checkNotNull(discount);
        if (items.isEmpty()) {
            throw new CartIsEmptyException();
        }
        PriceDiscounter discounter = new PriceDiscounter(discount);
        items = items.stream().map(discounter).collect(Collectors.toSet());
    }
}
