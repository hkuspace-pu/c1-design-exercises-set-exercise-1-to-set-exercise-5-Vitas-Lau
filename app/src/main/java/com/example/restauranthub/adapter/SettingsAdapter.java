package com.example.restauranthub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.activity.SettingsActivity;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private List<SettingsActivity.SettingsItem> settingsList;
    private Context context;

    public SettingsAdapter(List<SettingsActivity.SettingsItem> settingsList) {
        this.settingsList = settingsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingsActivity.SettingsItem item = settingsList.get(position);
        holder.ivIcon.setImageResource(item.iconRes);
        holder.tvTitle.setText(item.title);
        holder.tvSubtitle.setText(item.subtitle);

        holder.itemView.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(context, item.destinationActivity);
                context.startActivity(intent);
            } catch (Exception e) {
                // Fallback or log error if activity not found
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle, tvSubtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubtitle = itemView.findViewById(R.id.tvSubtitle);
        }
    }
}