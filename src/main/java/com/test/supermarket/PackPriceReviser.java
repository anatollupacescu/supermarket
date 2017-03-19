package com.test.supermarket;

import com.test.supermarket.domain.Pack;

import java.util.Set;
import java.util.function.Function;

public interface PackPriceReviser extends Function<Pack, Long> {

    boolean canBeApplied(Set<Pack> items);
}
