package com.example.uiprototype.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uiprototype.R;
import com.example.uiprototype.databinding.ActivityHomeBinding;
import com.example.uiprototype.RentLockerActivity;
import com.example.uiprototype.ViewLockersActivity;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupUI();
        setupNavigation();
    }

    private void setupUI() {

        binding.btnRentLocker.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, RentLockerActivity.class))
        );


        binding.btnViewLocker.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ViewLockersActivity.class))
        );

        // ACCESS DEMO
        binding.btnAccessActivity.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, AccessActivity.class))
        );
    }

    private void setupNavigation() {

        binding.topAppBar.setNavigationOnClickListener(v ->
                binding.drawerLayout.open()
        );

        binding.navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }

            if (id == R.id.nav_logout) {
                finish();
            }

            binding.drawerLayout.close();
            return true;
        });
    }
}
