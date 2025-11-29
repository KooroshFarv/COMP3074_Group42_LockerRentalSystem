package com.example.uiprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;
import com.example.uiprototype.ui.LockerAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.example.uiprototype.ui.LockerDetailsActivity;


import java.util.List;

public class RentLockerActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Rent a Locker");
        tb.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
        });

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        refreshList(recycler);
    }

    private void refreshList(RecyclerView recycler) {
        List<Locker> available = LockerRepository.getInstance().getAvailable();
        LockerAdapter adapter = new LockerAdapter(available, true, locker -> {
            boolean ok = LockerRepository.getInstance().rentLocker(locker.getId(), "test@example.com");
            Toast.makeText(this, ok ? "Locker #" + locker.getId() + " rented!" : "Sorry, not available.", Toast.LENGTH_SHORT).show();
            if (ok) refreshList(recycler);
        },
        locker -> {
            Intent i = new Intent(this, LockerDetailsActivity.class);
            i.putExtra("lockerId", locker.getId());
            startActivity(i);
            overridePendingTransition(R.anim.slide_to_right, R.anim.fade_out);
        }
        );
        recycler.setAdapter(adapter);
    }

}