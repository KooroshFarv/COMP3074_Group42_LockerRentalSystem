package com.example.uiprototype;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;
import com.example.uiprototype.ui.LockerAdapter;
import java.util.List;

public class ViewLockersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);
        setTitle("All Lockers");

        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Locker> data = LockerRepository.getInstance().getAll();
        rv.setAdapter(new LockerAdapter(data, false, null));
    }
}
