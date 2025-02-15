package com.example.signup_sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {

    private EditText email, password;
    private Button loginButton;
    private com.example.database.DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new com.example.database.DatabaseHelper(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String emailAddress = email.getText().toString();
            String pass = password.getText().toString();

            if (!emailAddress.isEmpty() && !pass.isEmpty()) {
                boolean isUserValid = dbHelper.checkUser(emailAddress, pass);
                if (isUserValid) {
                    Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // Navigate to the next screen, for example, a dashboard activity
                } else {
                    Toast.makeText(login.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(login.this, "Please fill both fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}