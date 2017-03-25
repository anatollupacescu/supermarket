package com.test.supermarket;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class Pack {

    private final Item item;
    private final int itemCount;
    private final long discount;

    public Pack(Item item, int count) {
        Preconditions.checkNotNull(item);
        Preconditions.checkArgument(count > 0);
        this.item = item;
        this.itemCount = count;
        this.discount = 0;
    }

    public Pack(Pack newPack, Pack existingPack) {
        this.item = newPack.getItem();
        this.itemCount = newPack.getItemCount() + existingPack.getItemCount();
        this.discount = newPack.getDiscount() + existingPack.getDiscount();
    }

    private Pack(Pack pack, long discount) {
        this.item = pack.getItem();
        this.itemCount = pack.getItemCount();
        this.discount = discount;
    }

    public Item getItem() {
        return item;
    }

    public int getItemCount() {
        return itemCount;
    }

    public long getTotalPrice() {
        return item.getPrice() * itemCount - discount;
    }

    public Pack copyWithDiscount(long discount) {
        Preconditions.checkArgument(discount > 0);
        return new Pack(this, discount);
    }

    public long getDiscount() {
        return discount;
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

    @Override
    public String toString() {
        return "Pack{" +
                "item=" + item +
                ", itemCount=" + itemCount +
                ", discount=" + discount +
                '}';
    }
}
