package com.example.recycler_view_romanov;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PopularActivity extends AppCompatActivity {

    RecyclerView CategoryList, CardList;
    TextView TvNamePage;
    CategoryAdapter CategoryAdapter;
    ArrayList<Category> Categorys = new ArrayList<>();
    ArrayList<Item> Items = new ArrayList<>();
    public Context Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        Context = this;
        Categorys = CategoryContex.All();

        Bundle arguments = getIntent().getExtras();
        Integer IdCategory = Integer.valueOf(arguments.get("Category").toString());

        CategoryList = findViewById(R.id.category_list);
        CardList = findViewById(R.id.card_list);
        TvNamePage = findViewById(R.id.tv_name_page);

        if(IdCategory != -1) {
            Category SelectCategory = Categorys.stream()
                    .filter(item -> item.Id == IdCategory)
                    .findAny()
                    .orElse(null);

            SelectCategory.Active = true;
            TvNamePage.setText(SelectCategory.Name);

            CategoryAdapter = new CategoryAdapter(this, Categorys, CategoryClick);
            CategoryList.setAdapter(CategoryAdapter);
        } else {
            CategoryList.setVisibility(View.GONE);

            TextView TvNameCategory = findViewById(R.id.tv_name_category);
            TvNameCategory.setVisibility(View.GONE);
        }

        Items = IdCategory == -1 ? ItemContex.All() : ItemContex.GetByCategory(IdCategory);

        CardList.setLayoutManager(new GridLayoutManager(this, 2));

        ItemAdapter CardAdapter = new ItemAdapter(this, Items, MainActivity.init.AddBasket);
        CardList.setAdapter(CardAdapter);
    }

    iOnClickInterface CategoryClick = new iOnClickInterface() {
        @Override
        public void setClick(View view, int position) {
            for (Category Item : Categorys) {
                Item.Active = false;
            }

            Category SelectCategory = Categorys.get(position);
            SelectCategory.Active = true;

            CategoryList.setAdapter(CategoryAdapter);

            TvNamePage.setText(SelectCategory.Name);

            Items = ItemContex.GetByCategory(SelectCategory.Id);
            ItemAdapter CardAdapter = new ItemAdapter(Context, Items);
            CardList.setAdapter(CardAdapter);
        }
    };

    public void ClosePopularActivity(View view) { finish(); }
}