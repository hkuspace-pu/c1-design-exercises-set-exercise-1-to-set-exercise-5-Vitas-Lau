package com.example.restaurantapp;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;
import com.example.restaurantapp.adapter.ReservationAdapter;
import com.example.restaurantapp.model.Reservation;
import java.util.*;

public class ReservationFragment extends Fragment {

    private static final String ARG_RESERVATIONS = "reservations";
    private RecyclerView recyclerView;

    public static ReservationFragment newInstance(ArrayList<Reservation> reservations) {
        ReservationFragment f = new ReservationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RESERVATIONS, reservations);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_reservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Reservation> list = (ArrayList<Reservation>) getArguments().getSerializable(ARG_RESERVATIONS);
        recyclerView.setAdapter(new ReservationAdapter(list != null ? list : new ArrayList<>()));

        return view;
    }
}