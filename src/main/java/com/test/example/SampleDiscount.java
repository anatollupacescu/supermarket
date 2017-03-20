package com.test.example;

import com.test.supermarket.PackPriceReviser;
import com.test.supermarket.domain.Pack;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SampleDiscount implements PackPriceReviser {

    private final Set<String> brandsWithDiscount = new HashSet<>();

    @Override
    public boolean canBeAppliedToCart(Set<Pack> items) {
        Map<String, List<Pack>> brandMap = items.stream()
                .collect(Collectors.groupingBy(pack -> pack.getItem().getBrand()));
        brandMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .forEach(brandsWithDiscount::add);
        return !brandsWithDiscount.isEmpty();
    }

    @Override
    public boolean canBeAppliedToPack(Pack pack) {
        return brandsWithDiscount.contains(pack.getItem().getBrand());
    }

    @Override
    public Long apply(Pack pack) {
        return Math.round(Math.ceil(pack.getItem().getPrice() * 0.9));
    }
}
