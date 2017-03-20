package com.test.supermarket;

import com.google.common.base.Preconditions;
import com.test.supermarket.domain.Cart;
import com.test.supermarket.domain.Pack;
import com.test.supermarket.exception.CartIsEmptyException;

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

    public void useDiscounter(PackMapper discounter) {
        Preconditions.checkNotNull(discounter);
        if (items.isEmpty()) {
            throw new CartIsEmptyException();
        }
        if (discounter.canBeApplied(items)) {
            items = items.stream().map(discounter).collect(Collectors.toSet());
        }
    }
}
