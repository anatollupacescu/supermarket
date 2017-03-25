package com.test.supermarket;

import com.google.common.base.Preconditions;

import java.util.Optional;

public class Cart {

    private final CartItems items = new CartItems();

    public CartItems getItems() {
        return new CartItems(items);
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

    public static class PackNotFoundException extends RuntimeException {
    }
}
