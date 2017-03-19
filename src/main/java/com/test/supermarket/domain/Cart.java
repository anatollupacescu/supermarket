package com.test.supermarket.domain;

import com.google.common.base.Preconditions;
import com.test.supermarket.exception.PackNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Cart {

    private final Set<Pack> items = new HashSet<>();

    public Set<Pack> getItems() {
        return new HashSet<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addItem(Item item, int amount) {
        Preconditions.checkNotNull(item);
        Preconditions.checkArgument(amount > 0);
        Pack pack = new Pack(item, amount);
        if (!packAdded(pack)) {
            updatePack(pack);
        }
    }

    private void updatePack(Pack newPack) {
        Optional<Pack> existinPackOptional = items.stream().filter(newPack::equals).findAny();
        if (existinPackOptional.isPresent()) {
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
}