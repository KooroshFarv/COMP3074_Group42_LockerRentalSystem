package com.example.uiprototype;

import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;
import com.example.uiprototype.ui.LockerAdapter;
import com.example.uiprototype.ui.LockerDetailsActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class ViewLockersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.my_lockers_title);
        tb.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
        });

        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        TextView emptyView = findViewById(R.id.emptyView);

        String userEmail = "test@example.com";

        List<Locker> myList = LockerRepository.getInstance().getMyLockers(userEmail);

        emptyView.setVisibility(myList.isEmpty() ? View.VISIBLE : View.GONE);

        rv.setAdapter(new LockerAdapter(
                myList,
                false,
                locker -> {
                    boolean ok = LockerRepository.getInstance().returnLocker(locker.getId());
                    Toast.makeText(this, ok ? "Locker returned!" : "Error returning locker", Toast.LENGTH_SHORT).show();
                    if (ok) recreate();
                },
                locker -> {
                    Intent i = new Intent(this, LockerDetailsActivity.class);
                    i.putExtra("lockerId", locker.getId());
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
                }
        ));
    }
}
