package com.example.restauranthub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {

    private List<TimeSlot> timeSlots;
    private int selectedPosition = -1;
    private OnTimeSlotClickListener listener;

    public interface OnTimeSlotClickListener {
        void onTimeSlotSelected(String time);
    }

    public TimeSlotAdapter(List<TimeSlot> timeSlots, OnTimeSlotClickListener listener) {
        this.timeSlots = timeSlots;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeSlot slot = timeSlots.get(position);

        holder.tvTime.setText(slot.time);
        holder.tvStatus.setText(slot.available ? "Available" : "Booked");
        holder.tvStatus.setTextColor(slot.available ? 0xFF4CAF50 : 0xFFFF5252);

        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.time_slot_selected);
        } else if (slot.available) {
            holder.itemView.setBackgroundResource(R.drawable.time_slot_background);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.time_slot_booked);
        }

        holder.itemView.setOnClickListener(v -> {
            if (slot.available) {
                int previous = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(previous);
                notifyItemChanged(selectedPosition);
                if (listener != null) {
                    listener.onTimeSlotSelected(slot.time);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }

    public static class TimeSlot {
        String time;
        boolean available;

        public TimeSlot(String time, boolean available) {
            this.time = time;
            this.available = available;
        }
    }
}