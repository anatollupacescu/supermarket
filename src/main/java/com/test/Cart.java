package com.test;

import com.google.common.base.Preconditions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Cart {

    private final Set<Pack> items = new HashSet<>();

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

    private class PackNotFoundException extends RuntimeException {
    }
}
