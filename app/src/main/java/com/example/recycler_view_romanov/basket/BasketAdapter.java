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

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    public iOnClickInterface Delete, Cost;
    public LayoutInflater Inflater;
    public ArrayList<Basket> BasketItems;

    public BasketAdapter(Context context, ArrayList<Basket> basketItems, iOnClickInterface delete, iOnClickInterface cost) {
        this.Inflater = LayoutInflater.from(context);
        this.BasketItems = basketItems;
        this.Delete = delete;
        this.Cost = cost;
    }

    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = Inflater.inflate(R.layout.item_basket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BasketAdapter.ViewHolder holder, int position) {
        Basket basketItem = BasketItems.get(position);

        holder.tvName.setText(basketItem.Item.Name);
        holder.tvPrice.setText("₽ " + String.valueOf(basketItem.Item.Price));
        holder.tvCount.setText(String.valueOf(basketItem.Count));

        holder.llCount.setVisibility(View.VISIBLE);
        holder.bthDelete.setVisibility(View.GONE);

        holder.bthPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    basketItem.Count++;
                    holder.tvCount.setText(String.valueOf(basketItem.Count));
                    if (Cost != null) {
                        Cost.setClick(view, pos);
                    }
                }
            }
        });

        holder.bthMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && basketItem.Count > 1) {
                    basketItem.Count--;
                    holder.tvCount.setText(String.valueOf(basketItem.Count));
                    if (Cost != null) {
                        Cost.setClick(view, pos);
                    }
                }
            }
        });

        holder.bthDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && Delete != null) {
                    Delete.setClick(view, pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return BasketItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvPrice, tvCount;
        public ImageView bthPlus, bthMinus;
        public LinearLayout bthDelete, llCount;

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