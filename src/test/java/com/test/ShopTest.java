package com.test;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShopTest {

    @Test
    public void canInstantiate() {
        Shop shop = new Shop();
        assertNotNull(shop);
    }
}