package com.test.example;

import com.test.supermarket.PackMapper;
import com.test.supermarket.PackPriceReviser;
import com.test.supermarket.domain.Pack;
import com.test.supermarket.exception.IncorrectPriceException;

import java.util.Set;

public class PackPriceDiscounter extends PackMapper {

    public PackPriceDiscounter(PackPriceReviser discount) {
        super(discount);
    }

    @Override
    public boolean canBeApplied(Set<Pack> items) {
        return discount.canBeAppliedToCart(items);
    }

    @Override
    public Pack apply(Pack pack) {
        if (!discount.canBeAppliedToPack(pack)) {
            return pack;
        }
        Long newPrice = discount.apply(pack);
        if (newPrice > pack.getTotalPrice()) {
            throw new IncorrectPriceException(); //protection against badly written discounts
        }
        return pack.cloneWithPrice(newPrice);
    }
}