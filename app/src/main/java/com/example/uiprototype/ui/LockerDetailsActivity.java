package com.example.uiprototype.ui;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.uiprototype.R;
import com.example.uiprototype.data.LockerRepository;
import com.example.uiprototype.model.Locker;

public class LockerDetailsActivity extends AppCompatActivity {

    private  Locker locker;
    private final String userEmail = "test@example.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locker_details);


        int lockerId = getIntent().getIntExtra("lockerId", -1);
        locker = LockerRepository.getInstance().findLocker(lockerId);

        if (locker == null) {
            Toast.makeText(this, "Locker not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        TextView txtId = findViewById(R.id.detailLockerId);
        TextView txtSize = findViewById(R.id.detailLockerSize);
        TextView txtLocation = findViewById(R.id.detailLockerLocation);
        TextView txtStatus = findViewById(R.id.detailLockerStatus);
        TextView txtRentedBy = findViewById(R.id.detailLockerUser);
        Button btnAction = findViewById(R.id.detailLockerButton);


        txtId.setText("Locker #" + locker.getId());
        txtSize.setText("Size: " + locker.getSize());
        txtLocation.setText("Location: " + locker.getLocation());
        txtStatus.setText(locker.isAvailable() ? "Available" : "Occupied");
        txtRentedBy.setText("Rented By: " + (locker.getRentedBy() == null ? "Nobody" : locker.getRentedBy()));

        if(locker.isAvailable()) {
            btnAction.setText("Rent");
            btnAction.setOnClickListener(v -> {
                boolean ok = LockerRepository.getInstance().rentLocker(locker.getId(), userEmail);
                Toast.makeText(this, ok ? "Locker rented" : "Error", Toast.LENGTH_SHORT).show();
                finish();
            });
        }else {
            btnAction.setText("Return");
            btnAction.setOnClickListener(v -> {
                boolean ok = LockerRepository.getInstance().returnLocker(locker.getId());
                Toast.makeText(this, ok ? "Locker returned" : "Error", Toast.LENGTH_SHORT).show();
                finish();
            });
        }

    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
    }
}
