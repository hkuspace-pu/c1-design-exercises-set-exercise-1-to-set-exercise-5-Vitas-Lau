package com.example.restaurantapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.model.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuViewHolder> {

    private List<MenuItem> menuList;
    private List<MenuItem> menuListFull; // For search filtering

    public MenuItemAdapter(List<MenuItem> menuList) {
        this.menuList = menuList;
        this.menuListFull = new ArrayList<>(menuList);
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuList.get(position);
        holder.nameText.setText(item.getName());
        holder.priceText.setText(String.format(Locale.US, "$%.2f", item.getPrice()));
        holder.availabilitySwitch.setChecked(item.isAvailable());

        // TODO: Load real image with Glide/Picasso
        // For demo we keep placeholder
        holder.itemImage.setImageResource(R.drawable.placeholder_food);

        holder.editButton.setOnClickListener(v ->
                Toast.makeText(v.getContext(), "Edit " + item.getName(), Toast.LENGTH_SHORT).show());

        holder.deleteButton.setOnClickListener(v -> {
            menuList.remove(position);
            menuListFull.remove(item);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, menuList.size());
            Toast.makeText(v.getContext(), item.getName() + " deleted", Toast.LENGTH_SHORT).show();
        });

        holder.availabilitySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setAvailable(isChecked);
            // TODO: Update in backend
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void filter(String query) {
        menuList.clear();
        if (query.isEmpty()) {
            menuList.addAll(menuListFull);
        } else {
            query = query.toLowerCase();
            for (MenuItem item : menuListFull) {
                if (item.getName().toLowerCase().contains(query)) {
                    menuList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView nameText, priceText;
        Switch availabilitySwitch;
        TextView editButton, deleteButton;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            nameText = itemView.findViewById(R.id.item_name);
            priceText = itemView.findViewById(R.id.item_price);
            availabilitySwitch = itemView.findViewById(R.id.switch_availability);
            editButton = itemView.findViewById(R.id.btn_edit);
            deleteButton = itemView.findViewById(R.id.btn_delete);
        }
    }
}