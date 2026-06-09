package com.example.recycler_view_romanov;

import java.util.ArrayList;

public class ItemContex {
    public static ArrayList<Item> All() {
        ArrayList<Item> Items = new ArrayList<>();

        Items.add(new Item(0,"Universum", "STREETBEAT", 11499, 0));
        Items.add(new Item(1,"Jogo", "STREETBEAT", 9999, 0));
        Items.add(new Item(2,"Suede XL", "PUMA", 13999, 0));
        Items.add(new Item(3,"Solid Mid Concrete Pack", "STREETBEAT", 10999, 1));
        Items.add(new Item(4,"S74", "New Balance", 18999, 1));
        Items.add(new Item(5,"2002", "New Balance", 24999, 1));
        Items.add(new Item(6,"P-6000", "Nike", 18999, 2));
        Items.add(new Item(7,"Morphic Base", "PUMA", 14999, 2));
        Items.add(new Item(8,"Air Max 97", "Nike", 27999, 2));
        Items.add(new Item(9,"Air Force 1", "Nike", 21999, 3));
        Items.add(new Item(10,"Sound Freeloock", "Hiker", 9499, 3));
        Items.add(new Item(11,"CA Pro Classic II", "PUMA", 14999, 3));
        Items.add(new Item(12,"KD17", "Nike", 27999, 3));
        Items.add(new Item(13,"Air Flight 89 Low", "Nike", 22499, 3));

        return Items;
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