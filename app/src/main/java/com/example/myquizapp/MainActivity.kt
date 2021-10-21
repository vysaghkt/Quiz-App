package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nameEntered: EditText
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEntered = findViewById(R.id.name_et)
        startButton = findViewById(R.id.start_btn)

        startButton.setOnClickListener {
            if (nameEntered.text.isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra(Constants.USERNAME, nameEntered.text.toString())
                nameEntered.setText("")
                startActivity(intent)
            }
        }
    }
}