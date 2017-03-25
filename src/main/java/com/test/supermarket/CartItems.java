package com.test.supermarket;

import java.util.HashSet;

public class CartItems extends HashSet<Pack> {

    public CartItems() {
        super();
    }

    public CartItems(CartItems items) {
        super(items);
    }

    public long getTotalPrice() {
        return stream().mapToLong(Pack::getTotalPrice).sum();
    }
}
