package com.test;

import java.util.HashSet;
import java.util.Set;

public class Cart {

    private final Set<Pack> items = new HashSet<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addItem(Item item, int amount) {
        this.items.add(new Pack(item, amount));
    }

    private class Pack {

        private final Item item;
        private final int count;

        public Pack(Item item, int count) {
            this.item = item;
            this.count = count;
        }
    }
}
