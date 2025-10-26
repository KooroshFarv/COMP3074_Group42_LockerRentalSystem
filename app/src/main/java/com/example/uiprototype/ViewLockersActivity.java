package com.example.uiprototype;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;
import com.example.uiprototype.ui.LockerAdapter;
import java.util.List;
import com.google.android.material.appbar.MaterialToolbar;

public class ViewLockersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("All Lockers");
        tb.setNavigationOnClickListener(v -> finish());

        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new LockerAdapter(LockerRepository.getInstance().getAll(), false, null));
    }
}