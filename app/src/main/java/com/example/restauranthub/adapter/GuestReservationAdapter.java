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
import com.example.restauranthub.activity.MyReservationsActivity;
import java.util.List;

public class GuestReservationAdapter extends RecyclerView.Adapter<GuestReservationAdapter.ViewHolder> {

    private List<MyReservationsActivity.Reservation> reservations;
    private Context context;

    public GuestReservationAdapter(Context context, List<MyReservationsActivity.Reservation> reservations) {
        this.context = context;
        this.reservations = reservations;
    }

    public void updateReservations(List<MyReservationsActivity.Reservation> newReservations) {
        this.reservations = newReservations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guest_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyReservationsActivity.Reservation res = reservations.get(position);
        holder.tvTitle.setText(res.title);
        holder.tvTime.setText(res.time);
        holder.tvStatus.setText(res.status);

        holder.btnModify.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditReservationActivity.class);
            intent.putExtra("title", res.title);
            intent.putExtra("time", res.time);
            intent.putExtra("date", res.date);
            // pass other data as needed
            context.startActivity(intent);
        });

        holder.btnCancel.setOnClickListener(v -> {
            // Simple cancel - remove from list (demo)
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                reservations.remove(pos);
                notifyItemRemoved(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvTime, tvStatus;
        Button btnModify, btnCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnModify = itemView.findViewById(R.id.btnModify);
            btnCancel = itemView.findViewById(R.id.btnCancel);
        }
    }
}