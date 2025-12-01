package com.example.uiprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uiprototype.ui.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button continueButton;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        continueButton = findViewById(R.id.btnContinue);
        createAccountButton = findViewById(R.id.btnCreateAccount);

        continueButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this,
                        "Please enter both email and password.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            String savedEmail = prefs.getString("email", null);
            String savedPassword = prefs.getString("password", null);

            if (savedEmail == null || savedPassword == null) {
                Toast.makeText(LoginActivity.this,
                        "No account found. Please create an account first.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!email.equalsIgnoreCase(savedEmail) || !password.equals(savedPassword)) {
                Toast.makeText(LoginActivity.this,
                        "Email or password is incorrect.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
            finish();
        });

        createAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.slide_to_left);
        });
    }
}
