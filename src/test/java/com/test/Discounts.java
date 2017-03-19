package com.test;

import com.test.supermarket.PackPriceReviser;
import com.test.supermarket.domain.Pack;

import java.util.Set;

public interface Discounts {

    PackPriceReviser incorrectDiscount = new PackPriceReviser() {

        @Override
        public boolean canBeApplied(Set<Pack> items) {
            return true;
        }

        @Override
        public Long apply(Pack pack) {
            return pack.getItem().getPrice() * 2;
        }
    };

    PackPriceReviser halfPriceDiscountOnMoreThanOneItem = new PackPriceReviser() {

        @Override
        public boolean canBeApplied(Set<Pack> items) {
            return items.size() > 1;
        }

        @Override
        public Long apply(Pack pack) {
            return Math.round(Math.ceil(pack.getItem().getPrice() / 2));
        }
    };

    PackPriceReviser halfPriceDiscount = new PackPriceReviser() {

        @Override
        public boolean canBeApplied(Set<Pack> items) {
            return true;
        }

        @Override
        public Long apply(Pack pack) {
            return Math.round(Math.ceil(pack.getItem().getPrice() / 2));
        }
    };

    PackPriceReviser noDiscount = new PackPriceReviser() {

        @Override
        public boolean canBeApplied(Set<Pack> items) {
            return false;
        }

        @Override
        public Long apply(Pack pack) {
            return null;
        }
    };
}
