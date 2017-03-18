package com.test;

import com.google.common.base.Preconditions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cart {

    private final Set<Pack> items;

    public Cart() {
         this.items = new HashSet<>();
    }

    private Cart(Set<Pack> items) {
        this.items = items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addItem(Item item, int amount) {
        Preconditions.checkNotNull(item);
        Preconditions.checkArgument(amount > 0);
        Pack pack = new Pack(item, amount);
        if(!packAdded(pack)) {
            updatePack(pack);
        }
    }

    private void updatePack(Pack newPack) {
        Optional<Pack> existinPackOptional = items.stream().filter(newPack::equals).findAny();
        if(existinPackOptional.isPresent()) {
            Pack existingPack = existinPackOptional.get();
            Pack mergedPack = new Pack(newPack, existingPack);
            items.remove(existingPack);
            items.add(mergedPack);
        } else {
            throw new PackNotFoundException();
        }
    }

    private boolean packAdded(Pack pack) {
        return items.add(pack);
    }

    public Set<Pack> getItems() {
        return new HashSet<>(items);
    }

    public long getTotalPrice() {
        return items.stream().mapToLong(Pack::getTotalPrice).sum();
    }

    public Cart applyPriceDiscount(Discount discount) {
        Preconditions.checkNotNull(discount);
        if(isEmpty()) {
            throw new CartIsEmptyException();
        }
        PriceDiscounter discounter = new PriceDiscounter(discount);
        Set<Pack> items = this.items.stream().map(discounter::apply).collect(Collectors.toSet());
        return new Cart(items);
    }

    class PackNotFoundException extends RuntimeException {
    }

    class CartIsEmptyException extends RuntimeException {
    }
}
