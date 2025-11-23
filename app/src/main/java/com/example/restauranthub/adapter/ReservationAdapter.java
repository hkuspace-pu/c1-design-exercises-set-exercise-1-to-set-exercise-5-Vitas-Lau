package com.example.restauranthub.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.TypedValue;
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

        // Call button: Show phone number bigger in a dialog
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Call Guest");
                TextView phoneView = new TextView(context);
                phoneView.setText(res.phone);
                phoneView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f); // Fixed deprecation: Added unit SP
                phoneView.setPadding(16, 16, 16, 16);
                builder.setView(phoneView);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        // Cancel button: Delete booking (remove from list)
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cancel Reservation");
                builder.setMessage("Are you sure you want to cancel this reservation?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reservations.remove(pos);
                        notifyItemRemoved(pos);
                        notifyItemRangeChanged(pos, reservations.size());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
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