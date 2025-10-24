package com.example.uiprototype;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;
import com.example.uiprototype.ui.LockerAdapter;
import java.util.List;

public class RentLockerActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);
        setTitle("Rent a Locker");

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        refreshList();
    }

    private void refreshList() {
        List<Locker> available = LockerRepository.getInstance().getAvailable();
        LockerAdapter adapter = new LockerAdapter(
                available,
                true,
                locker -> {
                    boolean ok = LockerRepository.getInstance().rentLocker(locker.getId());
                    if (ok) {
                        Toast.makeText(this, "Locker #" + locker.getId() + " rented!", Toast.LENGTH_SHORT).show();
                        refreshList();
                    } else {
                        Toast.makeText(this, "Sorry, no longer available.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        recycler.setAdapter(adapter);
    }
}
