package com.test.supermarket;

import com.test.supermarket.domain.Pack;

import java.util.Set;
import java.util.function.Function;

public abstract class PackMapper implements Function<Pack, Pack> {

    protected final PackPriceReviser discount;

    public PackMapper(PackPriceReviser discount) {
        this.discount = discount;
    }

    public abstract boolean canBeApplied(Set<Pack> items);

    @Override
    public abstract Pack apply(Pack pack);
}
