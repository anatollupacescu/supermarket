package com.test.supermarket;

import java.util.function.Function;

public interface Discount extends Function<CartItems, CartItems> {

    boolean isApplicable(CartItems itm);
}
