package com.example.uiprototype;

<<<<<<< Gurnoor_Khurana
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
=======
import android.os.Bundle;
import android.view.View;
>>>>>>> main
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

<<<<<<< Gurnoor_Khurana
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnCreateAccount;
    private TextView textBackToLogin;

=======
>>>>>>> main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

<<<<<<< Gurnoor_Khurana
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        textBackToLogin = findViewById(R.id.textBackToLogin);

        btnCreateAccount.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // 1) Basic empty check
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                        CreateAccountActivity.this,
                        "Please fill in name, email, and password.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // 2) Email format check
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(
                        CreateAccountActivity.this,
                        "Please enter a valid email address.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // 3) Password rules: at least 8 chars and must contain a number
            if (password.length() < 8) {
                Toast.makeText(
                        CreateAccountActivity.this,
                        "Password must be at least 8 characters.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (!password.matches(".*\\d.*")) {
                Toast.makeText(
                        CreateAccountActivity.this,
                        "Password must contain at least one number.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // (optional) you could add more rules like uppercase, symbol, etc.
            // if (!password.matches(".*[A-Z].*")) { ... }

            // 4) Save user – store email in lower case so it's consistent
            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            prefs.edit()
                    .putString("name", name)
                    .putString("email", email.toLowerCase())
                    .putString("password", password)
                    .apply();

            Toast.makeText(
                    CreateAccountActivity.this,
                    "Account created! You can now log in.",
                    Toast.LENGTH_SHORT
            ).show();

            // Go back to Login screen
            finish();
        });

        textBackToLogin.setOnClickListener(v -> finish());
=======
        // Find views
        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        TextView textBackToLogin = findViewById(R.id.textBackToLogin);

        // "Create Account" button click
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CreateAccountActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Account created for " + name + "!", Toast.LENGTH_LONG).show();

                    // Clear inputs
                    etName.setText("");
                    etEmail.setText("");
                    etPassword.setText("");
                }
            }
        });

        // "Back to Login" TextView click
        textBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to LoginActivity
            }
        });
>>>>>>> main
    }
}
