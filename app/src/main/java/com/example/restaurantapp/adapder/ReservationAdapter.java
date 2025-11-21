package com.example.restaurantapp.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.model.Reservation;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private List<Reservation> reservations;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation r = reservations.get(position);
        holder.guestName.setText(r.getGuestName());
        holder.time.setText(timeFormat.format(r.getDateTime()));
        holder.partySize.setText(r.getPartySize() + " guests");
        holder.phone.setText(r.getPhone());

        holder.btnEdit.setOnClickListener(v -> Toast.makeText(v.getContext(), "Edit " + r.getGuestName(), Toast.LENGTH_SHORT).show());
        holder.btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + r.getPhone()));
            v.getContext().startActivity(intent);
        });
        holder.btnCancel.setOnClickListener(v -> {
            reservations.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(v.getContext(), "Reservation cancelled", Toast.LENGTH_SHORT).show();
        });
    }

    @Override public int getItemCount() { return reservations.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView guestName, time, partySize, phone, btnEdit, btnCall, btnCancel;
        ViewHolder(View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.tv_guest_name);
            time = itemView.findViewById(R.id.tv_time);
            partySize = itemView.findViewById(R.id.tv_party_size);
            phone = itemView.findViewById(R.id.tv_phone);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnCall = itemView.findViewById(R.id.btn_call);
            btnCancel = itemView.findViewById(R.id.btn_cancel);
        }
    }
}