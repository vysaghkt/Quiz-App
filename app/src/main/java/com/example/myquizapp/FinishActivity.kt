package com.example.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinishActivity : AppCompatActivity() {

    private lateinit var scoreBoard: TextView
    private lateinit var finishButton: Button
    private lateinit var usernameTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val userName = intent.getStringExtra(Constants.USERNAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        scoreBoard = findViewById(R.id.score_tv)
        finishButton = findViewById(R.id.finish_btn)
        usernameTv = findViewById(R.id.user_name)

        usernameTv.text = userName
        scoreBoard.text = ("$correctAnswers/$totalQuestions").toString()

        finishButton.setOnClickListener {
            finish()
        }
    }
}