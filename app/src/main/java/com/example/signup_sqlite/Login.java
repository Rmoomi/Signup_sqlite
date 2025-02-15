package com.example.signup_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button loginButton;
    private com.example.signup_sqlite.DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Make sure this layout file exists

        dbHelper = new com.example.signup_sqlite.DatabaseHelper (this);

        // Initialize views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        // Login button click listener
        loginButton.setOnClickListener(v -> {
            String emailAddress = email.getText().toString();
            String pass = password.getText().toString();

            // Check if both email and password fields are filled
            if (!emailAddress.isEmpty() && !pass.isEmpty()) {
                // Check if the user exists in the database
                boolean isUserValid = dbHelper.checkUser(emailAddress, pass);
                if (isUserValid) {
                    Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // Navigate to the next screen (For example, a dashboard activity)
                    // You can use an Intent here to move to another Activity
                } else {
                    Toast.makeText(Login.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Login.this, "Please fill both fields.", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
