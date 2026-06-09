package com.example.recycler_view_romanov.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_view_romanov.R;
import com.example.recycler_view_romanov.iOnClickInterface;

import java.util.ArrayList;
import java.util.Map;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    public iOnClickInterface Delete, Cost;
    public LayoutInflater Inflater;
    public ArrayList<Basket> BasketItems;
    private Map<Integer, Integer> swipeStates;

    public BasketAdapter(Context context, ArrayList<Basket> basketItems,
                         iOnClickInterface delete, iOnClickInterface cost,
                         Map<Integer, Integer> swipeStates) {
        this.Inflater = LayoutInflater.from(context);
        this.BasketItems = basketItems;
        this.Delete = delete;
        this.Cost = cost;
        this.swipeStates = swipeStates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = Inflater.inflate(R.layout.item_basket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Basket item = BasketItems.get(position);

        holder.tvName.setText(item.Item.Name);
        holder.tvPrice.setText("₽ " + item.Item.Price);
        holder.tvCount.setText(String.valueOf(item.Count));

        Integer state = swipeStates.get(position);

        holder.itemView.setClickable(true);
        holder.bthPlus.setClickable(false);
        holder.bthMinus.setClickable(false);
        holder.bthDelete.setClickable(false);
        holder.llCount.setClickable(false);

        if (state == null || state == 0) {
            holder.llCount.setVisibility(View.GONE);
            holder.bthDelete.setVisibility(View.GONE);
        } else if (state == 1) {
            holder.llCount.setVisibility(View.GONE);
            holder.bthDelete.setVisibility(View.VISIBLE);

            holder.itemView.setClickable(false);
            holder.bthDelete.setClickable(true);
        } else if (state == 2) {
            holder.llCount.setVisibility(View.VISIBLE);
            holder.bthDelete.setVisibility(View.GONE);

            holder.itemView.setClickable(false);
            holder.llCount.setClickable(true);
            holder.bthPlus.setClickable(true);
            holder.bthMinus.setClickable(true);
        }

        holder.bthPlus.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && pos < BasketItems.size()) {
                BasketItems.get(pos).Count++;
                holder.tvCount.setText(String.valueOf(BasketItems.get(pos).Count));
                if (Cost != null) Cost.setClick(v, pos);
            }
        });

        holder.bthMinus.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && pos < BasketItems.size()) {
                Basket basketItem = BasketItems.get(pos);
                if (basketItem.Count > 1) {
                    basketItem.Count--;
                    holder.tvCount.setText(String.valueOf(basketItem.Count));
                    if (Cost != null) Cost.setClick(v, pos);
                }
            }
        });

        holder.bthDelete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && Delete != null) {
                Delete.setClick(v, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return BasketItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvCount;
        ImageView bthPlus, bthMinus;
        LinearLayout bthDelete, llCount;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvPrice = view.findViewById(R.id.tv_price);
            tvCount = view.findViewById(R.id.tv_count);
            bthPlus = view.findViewById(R.id.bthPlus);
            bthMinus = view.findViewById(R.id.bthMinus);
            bthDelete = view.findViewById(R.id.ll_delete);
            llCount = view.findViewById(R.id.ll_count);
        }
    }
}