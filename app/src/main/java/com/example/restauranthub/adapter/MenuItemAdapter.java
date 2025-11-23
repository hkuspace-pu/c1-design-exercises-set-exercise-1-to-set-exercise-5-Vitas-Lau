package com.example.restauranthub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.activity.MenuManageActivity;
import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    private List<MenuManageActivity.MenuItem> menuItems = new ArrayList<>();
    private boolean isManageMode;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(MenuManageActivity.MenuItem item);
        void onDeleteClick(MenuManageActivity.MenuItem item);
    }

    public MenuItemAdapter(List<MenuManageActivity.MenuItem> menuItems, boolean isManageMode, OnItemClickListener listener) {
        this.menuItems = menuItems;
        this.isManageMode = isManageMode;
        this.listener = listener;
    }

    public void updateMenuItems(List<MenuManageActivity.MenuItem> newItems) {
        this.menuItems = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_manage_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuManageActivity.MenuItem item = menuItems.get(position);
        holder.ivThumbnail.setImageResource(item.imageRes);
        holder.tvName.setText(item.name);
        holder.tvPrice.setText("$" + String.format("%.2f", item.price));
        holder.switchAvailability.setChecked(item.available);
        holder.tvAvailability.setText(item.available ? "Available" : "Unavailable"); // Fixed line
        holder.tvAvailability.setTextColor(item.available ? 0xFF000000 : 0xFFFF5252);

        if (isManageMode) {
            holder.btnEdit.setVisibility(View.VISIBLE);
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnEdit.setOnClickListener(v -> listener.onEditClick(item));
            holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(item));
        } else {
            holder.btnEdit.setVisibility(View.GONE);
            holder.btnDelete.setVisibility(View.GONE);
            holder.switchAvailability.setVisibility(View.GONE);
            holder.tvAvailability.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvName, tvPrice, tvAvailability;
        Switch switchAvailability;
        Button btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAvailability = itemView.findViewById(R.id.tvAvailability);
            switchAvailability = itemView.findViewById(R.id.switchAvailability);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}