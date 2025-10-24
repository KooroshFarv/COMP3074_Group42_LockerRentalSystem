package com.example.uiprototype.ui;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.uiprototype.HomeActivity;
import com.example.uiprototype.R;
import java.util.Locale;
import java.util.Random;

public class AccessActivity extends AppCompatActivity{
    private TextView textLockerId, textTimer, textAccessCode;
    private Button btnBackHome;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_code);



        textLockerId = findViewById(R.id.textLockerId);
        textAccessCode = findViewById(R.id.textAccessCode);
        textTimer = findViewById(R.id.textTimer);
        btnBackHome = findViewById(R.id.btnBackHome);

        // locker info
        Intent intent = getIntent();
        String lockerId = intent.getStringExtra("lockerId");
        textLockerId.setText("Locker " + lockerId);

        // random access code
        String accessCode = generateAccessCode();
        textAccessCode.setText("Access Code: " + accessCode);

        startTimer();

        btnBackHome.setOnClickListener(v -> {
            Intent homeIntent = new Intent(AccessActivity.this, HomeActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            finish();
    });

    }

    private String generateAccessCode() {
        String chars = "ABCDEFGHIJK12346789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<5; i++){
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }


    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
        @Override
        public void onTick(long millisUntilFinished){
            timeLeftInMillis = millisUntilFinished;
            updateTimerText();
        }

        @Override
            public void onFinish(){
            textTimer.setText("Access expired!");
            textAccessCode.setText("----");
        }

        }.start();
    }

    private void updateTimerText(){
        int minutes = (int) (timeLeftInMillis/ 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        textTimer.setText("Expires in: " + timeFormatted);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if ( countDownTimer != null) countDownTimer.cancel();
    }

}
