package com.example.uiprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button rentBtn = findViewById(R.id.btnRentLocker);
        Button viewBtn = findViewById(R.id.btnViewLocker);
        Button signOutBtn = findViewById(R.id.btnSignOut);

        if (rentBtn != null) {
            rentBtn.setOnClickListener(v ->
                    startActivity(new Intent(HomeActivity.this, RentLockerActivity.class))
            );
        }

        if (viewBtn != null) {
            viewBtn.setOnClickListener(v ->
                    startActivity(new Intent(HomeActivity.this, ViewLockersActivity.class))
            );
        }

        if (signOutBtn != null) {
            signOutBtn.setOnClickListener(v -> {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            });
        }
    }
}
