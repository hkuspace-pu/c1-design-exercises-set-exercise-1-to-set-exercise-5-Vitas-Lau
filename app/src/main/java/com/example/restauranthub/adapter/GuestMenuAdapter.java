package com.example.restauranthub.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.restauranthub.R;
import com.example.restauranthub.activity.MenuItemDetailActivity;
import com.example.restauranthub.activity.MenuBrowseActivity.MenuItem;
import java.util.List;

public class GuestMenuAdapter extends RecyclerView.Adapter<GuestMenuAdapter.ViewHolder> {

    private List<MenuItem> menuItems;

    public GuestMenuAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void updateMenuItems(List<MenuItem> newItems) {
        this.menuItems = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        
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
            intent.putExtra("imageUrl", item.imageUrl); // Pass URL instead of Res ID
            intent.putExtra("description", item.description);
            intent.putExtra("category", item.category);
            intent.putExtra("vegetarian", item.isVegetarian);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
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