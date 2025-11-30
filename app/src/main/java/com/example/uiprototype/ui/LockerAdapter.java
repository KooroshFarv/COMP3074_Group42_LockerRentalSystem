package com.example.uiprototype.ui;

import android.graphics.Color;
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


public class LockerAdapter extends RecyclerView.Adapter<LockerAdapter.ViewHolder> {

    private final List<Locker> lockers;
    private final boolean showRentButton;
    private final OnLockerActionListener actionListener;

    private final OnLockerClickListener clickListener;

    public interface OnLockerActionListener {
        void onLockerAction(Locker locker);
    }

    public interface OnLockerClickListener {
        void onLockerClick(Locker locker);
    }

    public LockerAdapter(List<Locker> lockers, boolean showRentButton, OnLockerActionListener actionListener,OnLockerClickListener clickListener) {
        this.lockers = lockers;
        this.showRentButton = showRentButton;
        this.actionListener = actionListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_locker, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Locker locker = lockers.get(position);


        h.txtTitle.setText("Locker #" + locker.getId() + " — " + locker.getSize());


        h.txtSubtitle.setText(locker.getLocation());

        if (locker.isAvailable()) {
            h.txtStatus.setText("Available");
            h.txtStatus.setTextColor(Color.parseColor("#2ECC71"));
        } else {
            h.txtStatus.setText("In Use");
            h.txtStatus.setTextColor(Color.parseColor("#E74C3C"));
        }


        h.itemView.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onLockerClick(locker);
        });

        if (showRentButton) {
            if (locker.isAvailable()) {
                h.btnAction.setVisibility(View.VISIBLE);
                h.btnAction.setText("Rent");
            } else {
                h.btnAction.setVisibility(View.GONE);
            }
        } else {
            if (!locker.isAvailable()) {
                h.btnAction.setVisibility(View.VISIBLE);
                h.btnAction.setText("Return");
            } else {
                h.btnAction.setVisibility(View.GONE);
            }
        }

        h.btnAction.setOnClickListener(v -> {
            if (actionListener != null) actionListener.onLockerAction(locker);
        });
    }

    @Override
    public int getItemCount() {
        return lockers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtSubtitle, txtStatus;
        Button btnAction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSubtitle = itemView.findViewById(R.id.txtSubtitle);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnAction = itemView.findViewById(R.id.btnRent);
        }
    }
}