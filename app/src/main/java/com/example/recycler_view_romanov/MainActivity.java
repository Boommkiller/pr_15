package com.example.recycler_view_romanov;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.example.recycler_view_romanov.basket.Basket;
import com.example.recycler_view_romanov.basket.BasketActivity;

public class MainActivity extends AppCompatActivity {

    public Context Context;
    public static MainActivity init;
    public static ArrayList<Basket> BasketList = new ArrayList<>();
    public ArrayList<Item> Items;

    public iOnClickInterface AddBasket = new iOnClickInterface() {
        @Override
        public void setClick(View view, int position) {

            if (position < 0 || position >= Items.size()) {
                return;
            }

            Item itemToAdd = Items.get(position);


            Basket existingBasket = null;
            for (Basket basket : BasketList) {
                if (basket.Item.Id == itemToAdd.Id) {
                    existingBasket = basket;
                    break;
                }
            }

            if (existingBasket == null) {

                Basket newBasket = new Basket(itemToAdd, 1);
                BasketList.add(newBasket);
                Toast.makeText(Context, "Товар '" + itemToAdd.Name + "' добавлен в корзину", Toast.LENGTH_SHORT).show();
            } else {

                existingBasket.Count++;
                Toast.makeText(Context, "Количество товара '" + itemToAdd.Name + "' увеличено", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init = this;
        Context = this;

        ArrayList<Category> Categorys = CategoryContex.All();
        Items = ItemContex.All();

        RecyclerView CategoryList = findViewById(R.id.category_list);
        RecyclerView CardList = findViewById(R.id.card_list);

        CategoryAdapter CategoryAdapter = new CategoryAdapter(this, Categorys, Click);
        CategoryList.setAdapter(CategoryAdapter);

        ItemAdapter CardAdapter = new ItemAdapter(this, Items, AddBasket);
        CardList.setAdapter(CardAdapter);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MenuNavigation fragment = new MenuNavigation();
        ft.add(R.id.menu_navigation, fragment);
        ft.commit();
    }

    public void OpenPopularView(View view) {
        Intent newIntent = new Intent(MainActivity.this, PopularActivity.class);
        newIntent.putExtra("Category", -1);
        startActivity(newIntent);
    }

    public void OpenBasketView(View view) {
        Intent newIntent = new Intent(MainActivity.this, BasketActivity.class);
        startActivity(newIntent);
    }

    iOnClickInterface Click = new iOnClickInterface() {
        @Override
        public void setClick(View view, int position) {
            Intent newIntent = new Intent(Context, PopularActivity.class);
            newIntent.putExtra("Category", position);
            startActivity(newIntent);
        }
    };
}