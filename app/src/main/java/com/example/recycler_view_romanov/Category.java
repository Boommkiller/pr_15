package com.example.recycler_view_romanov;

public class Category {
    public int Id;
    public String Name;
    public Boolean Active = false;

    public Category(int id, String name) {
        this.Id = id;
        this.Name = name;
    }
}