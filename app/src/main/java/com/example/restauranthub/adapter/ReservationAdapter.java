package com.example.restauranthub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.activity.EditReservationActivity;
import com.example.restauranthub.activity.StaffReservationsActivity;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private List<StaffReservationsActivity.Reservation> reservations;
    private Context context;

    public ReservationAdapter(Context context, List<StaffReservationsActivity.Reservation> reservations) {
        this.context = context;
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

        // Edit button click to switch to EditReservationActivity
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditReservationActivity.class);
                // Pass reservation data
                intent.putExtra("guestName", res.guestName);
                intent.putExtra("phone", res.phone);
                intent.putExtra("date", res.date);
                intent.putExtra("time", res.time);
                intent.putExtra("guests", res.guests);
                // TODO: Pass other fields like table, requests, status if added to Reservation class
                context.startActivity(intent);
            }
        });

        // TODO: Add handlers for Call and Cancel if needed
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGuestName, tvTimeParty, tvPhone;
        Button btnEdit, btnCall, btnCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGuestName = itemView.findViewById(R.id.tvGuestName);
            tvTimeParty = itemView.findViewById(R.id.tvTimeParty);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnCancel = itemView.findViewById(R.id.btnCancel);
        }
    }
}