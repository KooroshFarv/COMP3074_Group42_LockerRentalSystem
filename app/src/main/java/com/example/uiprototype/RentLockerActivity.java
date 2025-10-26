package com.example.uiprototype;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;
import com.example.uiprototype.ui.LockerAdapter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class RentLockerActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Rent a Locker");
        tb.setNavigationOnClickListener(v -> finish());

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        refreshList(recycler);
    }

    private void refreshList(RecyclerView recycler) {
        List<Locker> available = LockerRepository.getInstance().getAvailable();
        LockerAdapter adapter = new LockerAdapter(available, true, locker -> {
            boolean ok = LockerRepository.getInstance().rentLocker(locker.getId());
            Toast.makeText(this, ok ? "Locker #" + locker.getId() + " rented!" : "Sorry, no longer available.", Toast.LENGTH_SHORT).show();
            if (ok) refreshList(recycler);
        });
        recycler.setAdapter(adapter);
    }
}