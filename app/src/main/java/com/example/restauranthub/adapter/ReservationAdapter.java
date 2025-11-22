package com.example.restauranthub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.activity.StaffReservationsActivity;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private List<StaffReservationsActivity.Reservation> reservations;

    public ReservationAdapter(List<StaffReservationsActivity.Reservation> reservations) {
        this.reservations = reservations;
    }

    public void updateReservations(List<StaffReservationsActivity.Reservation> newReservations) {
        this.reservations = newReservations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StaffReservationsActivity.Reservation res = reservations.get(position);
        holder.tvGuestName.setText(res.guestName);
        holder.tvTimeParty.setText("⏰ " + res.time + " ♂ " + res.guests + " guests");
        holder.tvPhone.setText("☎ " + res.phone);
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGuestName, tvTimeParty, tvPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGuestName = itemView.findViewById(R.id.tvGuestName);
            tvTimeParty = itemView.findViewById(R.id.tvTimeParty);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }
}