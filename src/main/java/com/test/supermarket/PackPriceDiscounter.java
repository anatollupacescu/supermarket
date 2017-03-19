package com.test.supermarket;

import com.test.supermarket.domain.Item;
import com.test.supermarket.domain.Pack;
import com.test.supermarket.exception.IncorrectPriceException;

import java.util.Set;
import java.util.function.Function;

public class PackPriceDiscounter implements Function<Pack, Pack> {

    private final PackPriceReviser discount;

    public PackPriceDiscounter(PackPriceReviser discount) {
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

    public boolean canBeApplied(Set<Pack> items) {
        return discount.canBeApplied(items);
    }
}
