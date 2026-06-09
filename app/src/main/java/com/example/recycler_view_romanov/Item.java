package com.example.recycler_view_romanov;

public class Item {
    public int Id;
    public String Name;
    public String Modell;
    public Integer Price;
    public Integer IdCategory;


    public Item(int id, String name, String modell, Integer price, Integer idCategory) {
        this.Id = id;
        this.Name = name;
        this.Modell = modell;
        this.Price = price;
        this.IdCategory = idCategory;
    }
}