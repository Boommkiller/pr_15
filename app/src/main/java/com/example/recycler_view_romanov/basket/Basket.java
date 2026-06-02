package com.example.recycler_view_romanov.basket;

import com.example.recycler_view_romanov.Item;

public class Basket {
    public com.example.recycler_view_romanov.Item Item;

    public  Integer Count;
    public Basket(Item item, Integer count) {
        this.Item = item;
        this.Count = count;
    }
}
