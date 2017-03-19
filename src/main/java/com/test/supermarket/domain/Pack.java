package com.test.supermarket.domain;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class Pack {

    private final Item item;
    private final int itemCount;

    public Pack(Item item, int count) {
        Preconditions.checkNotNull(item);
        Preconditions.checkArgument(count > 0);
        this.item = item;
        this.itemCount = count;
    }

    public Pack(Pack newPack, Pack existingPack) {
        this.item = newPack.getItem();
        this.itemCount = newPack.getItemCount() + existingPack.getItemCount();
    }

    public Item getItem() {
        return item;
    }

    public int getItemCount() {
        return itemCount;
    }

    public long getTotalPrice() {
        return item.getPrice() * itemCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pack pack = (Pack) o;
        return Objects.equals(item, pack.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

}
