package com.example.recycler_view_romanov;

import java.util.ArrayList;

public class ItemContex {
    public static ArrayList<Item> All() {
        ArrayList<Item> Items = new ArrayList<>();

        Items.add(new Item("Universum", "STREETBEAT", 11499, 0));
        Items.add(new Item("Jogo", "STREETBEAT", 9999, 0));
        Items.add(new Item("Suede XL", "PUMA", 13999, 0));
        Items.add(new Item("Solid Mid Concrete Pack", "STREETBEAT", 10999, 1));
        Items.add(new Item("S74", "New Balance", 18999, 1));
        Items.add(new Item("2002", "New Balance", 24999, 1));
        Items.add(new Item("P-6000", "Nike", 18999, 2));
        Items.add(new Item("Morphic Base", "PUMA", 14999, 2));
        Items.add(new Item("Air Max 97", "Nike", 27999, 2));
        Items.add(new Item("Air Force 1", "Nike", 21999, 3));
        Items.add(new Item("Sound Freeloock", "Hiker", 9499, 3));
        Items.add(new Item("CA Pro Classic II", "PUMA", 14999, 3));
        Items.add(new Item("KD17", "Nike", 27999, 3));
        Items.add(new Item("Air Flight 89 Low", "Nike", 22499, 3));

        return Items; // возвращаем массив объектов
    }

    public static ArrayList<Item> GetByCategory(Integer idCategory) {
        if(idCategory == 0) return All();

        ArrayList<Item> Items = new ArrayList<>();

        for(Item Item : All()) {
            if(Item.IdCategory.equals(idCategory))
                Items.add(Item);
        }

        return Items;
    }
}