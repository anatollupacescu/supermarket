package com.test;

import com.test.supermarket.PackPriceReviser;
import com.test.supermarket.domain.Pack;

import java.util.Set;

class TestDiscounts {

    static final PackPriceReviser incorrectDiscount = new PackPriceReviser() {

        @Override
        public boolean canBeAppliedToCart(Set<Pack> items) {
            return true;
        }

        @Override
        public boolean canBeAppliedToPack(Pack pack) {
            return true;
        }

        @Override
        public Long apply(Pack pack) {
            return pack.getItem().getPrice() * 2;
        }
    };

    static final PackPriceReviser halfPriceDiscountOnMoreThanOneItem = new PackPriceReviser() {

        @Override
        public boolean canBeAppliedToCart(Set<Pack> items) {
            return items.size() > 1;
        }

        @Override
        public boolean canBeAppliedToPack(Pack pack) {
            return true;
        }

        @Override
        public Long apply(Pack pack) {
            return Math.round(Math.ceil(pack.getItem().getPrice() / 2));
        }
    };

    static final PackPriceReviser halfPriceDiscount = new PackPriceReviser() {

        @Override
        public boolean canBeAppliedToCart(Set<Pack> items) {
            return true;
        }

        @Override
        public boolean canBeAppliedToPack(Pack pack) {
            return true;
        }

        @Override
        public Long apply(Pack pack) {
            return Math.round(Math.ceil(pack.getItem().getPrice() / 2));
        }
    };

    static final PackPriceReviser noDiscount = new PackPriceReviser() {

        @Override
        public boolean canBeAppliedToCart(Set<Pack> items) {
            return false;
        }

        @Override
        public boolean canBeAppliedToPack(Pack pack) {
            return false;
        }

        @Override
        public Long apply(Pack pack) {
            return null;
        }
    };
}
