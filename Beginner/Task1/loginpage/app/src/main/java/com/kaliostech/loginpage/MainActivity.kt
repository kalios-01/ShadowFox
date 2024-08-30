package com.kaliostech.loginpage

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        emailInputLayout = findViewById(R.id.email)
        emailEditText = findViewById(R.id.emailEditText)
        passwordInputLayout = findViewById(R.id.password)
        passwordEditText = findViewById(R.id.passwordEditText)

        // Set up login button click listener
        val loginButton: TextView = findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            emailInputLayout.error = "Email cannot be empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.error = "Please enter a valid email"
        } else {
            emailInputLayout.error = null // Clear error
        }

        if (password.isEmpty()) {
            passwordInputLayout.error = "Password cannot be empty"
        } else {
            passwordInputLayout.error = null // Clear error
        }

        // Proceed with login logic if inputs are valid
        if (emailInputLayout.error == null && passwordInputLayout.error == null) {
            // open another activity
            val intent = Intent(this, DashBoard::class.java)
            startActivity(intent)

        }
    }
}
