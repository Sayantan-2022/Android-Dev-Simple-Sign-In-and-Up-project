package com.example.gradientdesign

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var databasesi : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val etUsernamesi = findViewById<TextInputEditText>(R.id.etUsernamesi)
        val etPasswordsi = findViewById<TextInputEditText>(R.id.etPasswordsi)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        btnSignIn.setOnClickListener {
            val username = etUsernamesi.text.toString()
            val password = etPasswordsi.text.toString()
            if(checkBox.isChecked) {
                if(username.isNotEmpty() && password.isNotEmpty()) {
                    readData(username, password)
                } else {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkBox.buttonTintList = ColorStateList.valueOf(Color.RED)
                Toast.makeText(this, "Please tick the Checkbox", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(username: String, password: String) {
        databasesi = FirebaseDatabase.getInstance().getReference("Users")
        databasesi.child(username).get().addOnSuccessListener {
            if (it.exists()) {
                val passwordDB = it.child("password").value
                if (passwordDB == password) {
                    val name = it.child("name").value
                    val mail = it.child("mail").value

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("name", name.toString())
                    intent.putExtra("mail", mail.toString())
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to read data. Please try again!", Toast.LENGTH_SHORT).show()
        }
    }
}