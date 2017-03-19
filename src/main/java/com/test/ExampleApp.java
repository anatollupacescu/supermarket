package com.test;

import com.test.supermarket.CartPriceCalculator;
import com.test.supermarket.PackPriceDiscounter;
import com.test.supermarket.PackPriceReviser;
import com.test.supermarket.domain.Cart;
import com.test.supermarket.domain.Item;
import com.test.supermarket.domain.Pack;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExampleApp {

    private static PackPriceReviser twoItemsFromTheSameBrandGet10PercentDiscount = new PackPriceReviser() {

        public Set<String> brandsWithDiscount;

        @Override
        public boolean canBeApplied(Set<Pack> items) {
            Map<String, List<Pack>> brandMap = items.stream()
                    .collect(Collectors.groupingBy(pack -> pack.getItem().getBrand()));
            brandsWithDiscount = brandMap.entrySet().stream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
            return !brandsWithDiscount.isEmpty();
        }

        @Override
        public Long apply(Pack pack) {
            return Math.round(Math.ceil(pack.getItem().getPrice() * 0.9));
        }
    };

    public static void main(String[] args) {
        Cart cart = new Cart();
        Item phone = new Item("phone", "lg", 100L);
        Item smartwatch = new Item("smartwatch", "lg", 50L);
        cart.addItem(phone, 1);
        cart.addItem(smartwatch, 1);
        CartPriceCalculator calculator = new CartPriceCalculator(cart);
        System.out.println("Price before discount: " + calculator.getPrice());
        PackPriceDiscounter discounter = new PackPriceDiscounter(twoItemsFromTheSameBrandGet10PercentDiscount);
        calculator.useDiscounter(discounter);
        System.out.println("Price after discount: " + calculator.getPrice());
    }
}
