package com.example.restauranthub.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.restauranthub.R;
import com.example.restauranthub.activity.MenuItemDetailActivity;
import com.example.restauranthub.activity.MenuBrowseActivity.MenuItem;

public class GuestMenuAdapter extends ListAdapter<MenuItem, GuestMenuAdapter.ViewHolder> {

    public GuestMenuAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<MenuItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MenuItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull MenuItem oldItem, @NonNull MenuItem newItem) {
                    return oldItem.name.equals(newItem.name);
                }

                @Override
                public boolean areContentsTheSame(@NonNull MenuItem oldItem, @NonNull MenuItem newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = getItem(position);
        
        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder_dish)
                .error(R.drawable.placeholder_dish)
                .centerCrop()
                .into(holder.ivDish);

        holder.tvDishName.setText(item.name);
        holder.tvPrice.setText("$" + String.format("%.2f", item.price));

        // Click on the entire card to open detail page
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MenuItemDetailActivity.class);
            intent.putExtra("name", item.name);
            intent.putExtra("price", item.price);
            intent.putExtra("imageUrl", item.imageUrl);
            intent.putExtra("description", item.description);
            intent.putExtra("category", item.category);
            intent.putExtra("vegetarian", item.isVegetarian);
            v.getContext().startActivity(intent);
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDish;
        TextView tvDishName, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDish = itemView.findViewById(R.id.ivDish);
            tvDishName = itemView.findViewById(R.id.tvDishName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}