package com.example.uiprototype;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.uiprototype.ui.AccessActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String fullName = prefs.getString("name", "");

        TextView homeTitle = findViewById(R.id.homeTitle);
        homeTitle.setText("Welcome, " + fullName + "!");

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        topAppBar = findViewById(R.id.topAppBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                topAppBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home){

            } else if (id == R.id.nav_rent) {
                startActivity(new Intent(this, RentLockerActivity.class));
                overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
            }else if (id == R.id.nav_view) {
                startActivity(new Intent (this, ViewLockersActivity.class));
                overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
            }else if(id == R.id.nav_logout) {
                Intent i = new Intent(this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
                finish();

            }
            drawerLayout.closeDrawers();
            return true;
        });


        Button rentBtn = findViewById(R.id.btnRentLocker);
        Button viewBtn = findViewById(R.id.btnViewLocker);
        Button accessBtn = findViewById(R.id.btnAccessActivity);

        if (accessBtn != null) {
            accessBtn.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, AccessActivity.class));
                overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
            });
        }

        if (rentBtn != null) {
            rentBtn.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, RentLockerActivity.class));
                overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
            });

        }


        if (viewBtn != null) {
            viewBtn.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, ViewLockersActivity.class));
                overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
            });
        }

    }
}
