package com.example.restauranthub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.activity.MenuBrowseActivity;
import java.util.List;

public class GuestMenuAdapter extends RecyclerView.Adapter<GuestMenuAdapter.ViewHolder> {

    private List<MenuBrowseActivity.MenuItem> menuItems;

    public GuestMenuAdapter(List<MenuBrowseActivity.MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void updateMenuItems(List<MenuBrowseActivity.MenuItem> newItems) {
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
        MenuBrowseActivity.MenuItem item = menuItems.get(position);
        holder.ivDish.setImageResource(item.imageRes);
        holder.tvDishName.setText(item.name);
        holder.tvPrice.setText("$" + String.format("%.2f", item.price));
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