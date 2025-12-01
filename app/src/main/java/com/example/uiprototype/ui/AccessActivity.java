package com.example.uiprototype.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uiprototype.R;

import java.util.Locale;
import java.util.Random;

public class AccessActivity extends AppCompatActivity {

    private TextView textLockerId, textTimer, textAccessCode;
    private Button btnBackHome;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_code);

        textLockerId = findViewById(R.id.textLockerId);
        textTimer = findViewById(R.id.textTimer);
        textAccessCode = findViewById(R.id.textAccessCode);
        btnBackHome = findViewById(R.id.btnBackHome);

        generateAccessCode();
        startTimer();

        btnBackHome.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
        });
    }

    private void generateAccessCode() {
        Random random = new Random();
        int lockerId = random.nextInt(100) + 1;
        int code = random.nextInt(900000) + 100000;

        textLockerId.setText(String.valueOf(lockerId));
        textAccessCode.setText(String.valueOf(code));
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                textTimer.setText("Expired!");
            }
        }.start();
    }

    private void updateTimerText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        textTimer.setText(String.format(Locale.getDefault(), "%02d", seconds));
    }
}
