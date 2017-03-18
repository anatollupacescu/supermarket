package com.test;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PackTest {

    @Test
    public void canCreatePack() {
        Item item = new Item("bread", "brand", 10L);
        int count = 1;
        Pack pack = new Pack(item, count);
        assertNotNull(pack);
        assertThat(pack.getTotalPrice(), is(equalTo(item.getPrice())));
    }

    @Test(expected = NullPointerException.class)
    public void doesNotAcceptNullItem() {
        Item item = null;
        new Pack(item, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAcceptZeroAmount() {
        Item item = new Item("bread", "brand", 10L);
        new Pack(item, 0);
    }

    @Test
    public void canMergePacks() {
        Item item = new Item("bread", "brand", 10L);
        Pack pack1 = new Pack(item, 2);
        assertThat(pack1.getTotalPrice(), is(equalTo(2 * item.getPrice())));
        Pack pack2 = new Pack(item, 3);
        assertThat(pack2.getTotalPrice(), is(equalTo(3 * item.getPrice())));
        Pack mergedPack = new Pack(pack1, pack2);
        assertThat(mergedPack, is(notNullValue()));
        assertThat(mergedPack.getItem(), is(sameInstance(item)));
        assertThat(mergedPack.getItemCount(), is(equalTo(5)));
        assertThat(mergedPack.getTotalPrice(), is(equalTo(5 * item.getPrice())));
    }

}