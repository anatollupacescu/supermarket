package com.test;

import java.util.Objects;

public class Item {

    private final String name;
    private final String brand;
    private final long price;

    public Item(String type, String brand, long price) {
        this.name = type;
        this.brand = brand;
        this.price = price;
    }

    public Item(Item copyFrom, long price) {
        this.name = copyFrom.getName();
        this.brand = copyFrom.getBrand();
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price &&
                Objects.equals(name, item.name) &&
                Objects.equals(brand, item.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, price);
    }
}
