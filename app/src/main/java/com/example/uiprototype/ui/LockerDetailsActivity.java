package com.example.uiprototype.ui;

import android.content.Intent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uiprototype.R;
import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;
import com.google.android.material.appbar.MaterialToolbar;

public class LockerDetailsActivity extends AppCompatActivity {

    private Locker locker;
    // for now still using demo email
    private final String userEmail = "test@example.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locker_details);

        // toolbar back arrow -> go back to previous screen
        MaterialToolbar toolbar = findViewById(R.id.detailsToolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        int lockerId = getIntent().getIntExtra("lockerId", -1);
        locker = LockerRepository.getInstance().findLocker(lockerId);

        if (locker == null) {
            Toast.makeText(this, "Locker not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView txtId = findViewById(R.id.detailLockerId);
        TextView txtSize = findViewById(R.id.detailLockerSize);
        TextView txtPrice = findViewById(R.id.detailLockerPrice);
        TextView txtLocation = findViewById(R.id.detailLockerLocation);
        TextView txtStatus = findViewById(R.id.detailLockerStatus);
        TextView txtRentedBy = findViewById(R.id.detailLockerUser);
        Button btnAction = findViewById(R.id.detailLockerButton);

        txtId.setText("Locker #" + locker.getId());
        txtSize.setText("Size: " + locker.getSize());
        txtPrice.setText("Price: " + getPriceForSize(locker.getSize()));
        txtLocation.setText("Location: " + locker.getLocation());
        txtStatus.setText(locker.isAvailable() ? "Available" : "Occupied");
        txtRentedBy.setText("Rented By: " +
                (locker.getRentedBy() == null ? "Nobody" : locker.getRentedBy()));

        if (locker.isAvailable()) {
            btnAction.setText("Rent & Get Code");
            btnAction.setOnClickListener(v -> {
                boolean ok = LockerRepository.getInstance()
                        .rentLocker(locker.getId(), userEmail);
                if (ok) {
                    Toast.makeText(this, "Locker rented", Toast.LENGTH_SHORT).show();

                    // go to access screen with temp code
                    Intent accessIntent = new Intent(
                            LockerDetailsActivity.this,
                            AccessActivity.class
                    );
                    accessIntent.putExtra("lockerId",
                            String.valueOf(locker.getId()));
                    startActivity(accessIntent);
                    overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
                    finish();
                } else {
                    Toast.makeText(this, "Error renting locker", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            btnAction.setText("Return");
            btnAction.setOnClickListener(v -> {
                boolean ok = LockerRepository.getInstance()
                        .returnLocker(locker.getId());
                Toast.makeText(this,
                        ok ? "Locker returned" : "Error", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }

    private String getPriceForSize(String size) {
        if ("Small".equalsIgnoreCase(size)) {
            return "$5.00 / day";
        } else if ("Medium".equalsIgnoreCase(size)) {
            return "$8.00 / day";
        } else {
            return "$12.00 / day";
        }
    }

    @Override
    public void finish() {
        super.finish();
        // same animation style as other screens
        overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
    }
}
