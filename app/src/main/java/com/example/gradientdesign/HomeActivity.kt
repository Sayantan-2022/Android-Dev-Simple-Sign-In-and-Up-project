package com.example.gradientdesign

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val name = intent.getStringExtra("name")
        val mail = intent.getStringExtra("mail")

        val btnName = findViewById<Button>(R.id.btnName)
        val btnMail = findViewById<Button>(R.id.btnMail)
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvMail = findViewById<TextView>(R.id.tvMail)

        btnName.setOnClickListener {
            tvName.text = "Name: $name"
            tvWelcome.text = "Welcome,\n$name"
        }
        btnMail.setOnClickListener {
            tvMail.text = "Email: $mail"
        }
    }
}