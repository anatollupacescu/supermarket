package com.test;

import java.util.function.Function;

public class PriceDiscounter implements Function<Pack, Pack> {

    private final Discount discount;

    public PriceDiscounter(Discount discount) {
        this.discount = discount;
    }

    @Override
    public Pack apply(Pack pack) {
        Long newPrice = discount.apply(pack);
        if (newPrice > pack.getTotalPrice()) {
            throw new IncorrectPriceException(); //protection against badly written discounts
        }
        return new Pack(new Item(pack.getItem(), newPrice), pack.getItemCount());
    }

    class IncorrectPriceException extends RuntimeException {
    }
}
