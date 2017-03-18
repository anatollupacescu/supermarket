package com.test;

public class Item {

    private final String brand;
    private final long price;

    protected Item(String name, long price) {
        this.brand = name;
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }
}
