package com.example.recycler_view_romanov.basket;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycler_view_romanov.MainActivity;
import com.example.recycler_view_romanov.R;
import com.example.recycler_view_romanov.iOnClickInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasketActivity extends AppCompatActivity {

    private Map<Integer, Integer> swipeStates = new HashMap<>();

    public iOnClickInterface Delete = new iOnClickInterface() {
        @Override
        public void setClick(View view, int position) {
            if (position >= 0 && position < MainActivity.init.BasketList.size()) {
                MainActivity.init.BasketList.remove(position);
                swipeStates.clear();
                if (BasketRV.getAdapter() != null) {
                    BasketRV.getAdapter().notifyDataSetChanged();
                }
                CostCalculation();
            }
        }
    };

    public iOnClickInterface EventCost = new iOnClickInterface() {
        @Override
        public void setClick(View view, int position) {
            CostCalculation();
        }
    };

    public RecyclerView BasketRV;
    public TextView tvSum, tvAllSum;
    BasketAdapter BasketAdapter;
    Context Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Context = this;
        BasketRV = findViewById(R.id.basket_list);
        tvSum = findViewById(R.id.tv_sum);
        tvAllSum = findViewById(R.id.tv_all_sum);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(SwipeAdapter);
        itemTouchHelper.attachToRecyclerView(BasketRV);

        BasketAdapter = new BasketAdapter(this, MainActivity.init.BasketList, Delete, EventCost, swipeStates);
        BasketRV.setAdapter(BasketAdapter);
        CostCalculation();
    }

    ItemTouchHelper.SimpleCallback SwipeAdapter = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            int position = viewHolder.getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                return;
            }

            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                Resources r = getResources();
                float maxSwipe = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 58, r.getDisplayMetrics());

                if (dX < -maxSwipe) dX = -maxSwipe;
                else if (dX > maxSwipe) dX = maxSwipe;

                if (dX < -10) swipeStates.put(position, 1);
                else if (dX > 10) swipeStates.put(position, 2);
                else swipeStates.remove(position);

                viewHolder.itemView.setTranslationX(dX);

                View itemView = viewHolder.itemView;
                LinearLayout btnDelete = itemView.findViewById(R.id.ll_delete);
                LinearLayout btnCount = itemView.findViewById(R.id.ll_count);

                if (btnDelete != null && btnCount != null) {
                    if (swipeStates.getOrDefault(position, 0) == 1) {
                        btnDelete.setVisibility(View.VISIBLE);
                        btnCount.setVisibility(View.GONE);
                    } else if (swipeStates.getOrDefault(position, 0) == 2) {
                        btnDelete.setVisibility(View.GONE);
                        btnCount.setVisibility(View.VISIBLE);
                    } else {
                        btnDelete.setVisibility(View.GONE);
                        btnCount.setVisibility(View.GONE);
                    }
                }
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            int position = viewHolder.getAdapterPosition();

            viewHolder.itemView.setTranslationX(0);

            if (position != RecyclerView.NO_POSITION) {
                if (swipeStates.getOrDefault(position, 0) == 0) {
                    View itemView = viewHolder.itemView;
                    LinearLayout btnDelete = itemView.findViewById(R.id.ll_delete);
                    LinearLayout btnCount = itemView.findViewById(R.id.ll_count);
                    if (btnDelete != null) btnDelete.setVisibility(View.GONE);
                    if (btnCount != null) btnCount.setVisibility(View.GONE);
                }
                if (BasketRV.getAdapter() != null) {
                    BasketRV.getAdapter().notifyItemChanged(position);
                }
            }
        }
    };

    public void CostCalculation() {
        float ItemPrice = 0;
        for (Basket Item : MainActivity.init.BasketList) {
            ItemPrice += Item.Item.Price * Item.Count;
        }
        tvSum.setText("₽" + ItemPrice);
        ItemPrice += 60.20;
        tvAllSum.setText("₽" + ItemPrice);
    }

    public void ClosePopularActivity(View view) {
        finish();
    }
}