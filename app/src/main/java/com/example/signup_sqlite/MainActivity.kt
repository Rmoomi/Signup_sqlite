package com.example.signup_sqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var firstname: EditText
    private lateinit var lastname: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var signupButton: View
    private lateinit var dbHelper: DatabaseHelper
    //yes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup) // Make sure this is the correct layout file for your activity

        // Initialize your views here
        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirm_password)
        signupButton = findViewById(R.id.signup_button)

        dbHelper = DatabaseHelper(this)

        signupButton.setOnClickListener {
            val firstName = firstname.text.toString()
            val lastName = lastname.text.toString()
            val emailAddress = email.text.toString()
            val pass = password.text.toString()
            val confirmPass = confirmPassword.text.toString()

            // Check if all fields are filled
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && emailAddress.isNotEmpty() && pass.isNotEmpty()) {
                if (pass == confirmPass) {
                    // Insert user into the database
                    val isInserted = dbHelper.insertUser(firstName, lastName, emailAddress, pass)
                    if (isInserted) {
                        Toast.makeText(this@MainActivity, "Sign up successful!", Toast.LENGTH_SHORT).show()

                        // Go to the Login activity
                        val intent = Intent(this@MainActivity, login::class.java)
                        startActivity(intent)
                        finish() // Close the SignUpActivity
                    } else {
                        Toast.makeText(this@MainActivity, "Sign up failed. Try again.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
