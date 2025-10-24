package com.example.uiprototype.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uiprototype.R;
import com.example.uiprototype.model.Locker;
import java.util.List;

public class LockerAdapter extends RecyclerView.Adapter<LockerAdapter.LockerVH> {

    public interface OnRentClick { void onRent(Locker locker); }

    private final List<Locker> data;
    private final boolean showRentButton; // true on Rent screen
    private final OnRentClick rentClick;

    public LockerAdapter(List<Locker> data, boolean showRentButton, OnRentClick rentClick) {
        this.data = data;
        this.showRentButton = showRentButton;
        this.rentClick = rentClick;
    }

    @NonNull
    @Override
    public LockerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_locker, parent, false);
        return new LockerVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LockerVH h, int position) {
        Locker l = data.get(position);
        h.txtTitle.setText("Locker #" + l.getId() + " — " + l.getSize());
        h.txtSubtitle.setText(l.getLocation());
        h.txtStatus.setText(l.isAvailable() ? "Available" : "In Use");

        if (showRentButton) {
            h.btnRent.setVisibility(View.VISIBLE);
            h.btnRent.setOnClickListener(v -> {
                if (rentClick != null) rentClick.onRent(l);
            });
        } else {
            h.btnRent.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class LockerVH extends RecyclerView.ViewHolder {
        final TextView txtTitle, txtSubtitle, txtStatus;
        final Button btnRent;
        LockerVH(@NonNull View v) {
            super(v);
            txtTitle = v.findViewById(R.id.txtTitle);
            txtSubtitle = v.findViewById(R.id.txtSubtitle);
            txtStatus = v.findViewById(R.id.txtStatus);
            btnRent = v.findViewById(R.id.btnRent);
        }
    }
}
