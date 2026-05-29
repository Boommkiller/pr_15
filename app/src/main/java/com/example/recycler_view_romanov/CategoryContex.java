package com.example.recycler_view_romanov;

import java.util.ArrayList;

public class CategoryContex {
    public static ArrayList<Category> All() {
        ArrayList<Category> Categorys = new ArrayList<>();

        Categorys.add(new Category(0, "Все"));
        Categorys.add(new Category(1, "Outdoor"));
        Categorys.add(new Category(2, "Tennis"));
        Categorys.add(new Category(3, "Running"));

        return Categorys;
    }
}
