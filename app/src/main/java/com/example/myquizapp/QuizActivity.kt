package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userName: String

    private lateinit var quizToolbar: Toolbar
    private lateinit var questionTv: TextView
    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var questionCount: TextView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var submitButton: Button
    private lateinit var timerLayout: LinearLayout
    private lateinit var timeTv: TextView

    private var questionList: ArrayList<QuestionsModel>? = null
    private var currentQuestion = 1
    private var selectedOption = 0
    private var correctAnswers = 0

    private var totalTime: Long = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        userName = intent.getStringExtra(Constants.USERNAME).toString()

        quizToolbar = findViewById(R.id.quiz_toolbar)
        questionTv = findViewById(R.id.question_tv)
        image = findViewById(R.id.question_image)
        progressBar = findViewById(R.id.progressbar)
        questionCount = findViewById(R.id.question_count)
        optionOne = findViewById(R.id.opt_one)
        optionTwo = findViewById(R.id.opt_two)
        optionThree = findViewById(R.id.opt_three)
        submitButton = findViewById(R.id.btn_submit)
        timerLayout = findViewById(R.id.timer_layout)
        timeTv = findViewById(R.id.time_for_question)

        setSupportActionBar(quizToolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Quiz"
        }
        quizToolbar.setNavigationOnClickListener {
            setAlertDialog()
        }

        questionList = Constants.getQuestionList()

        progressBar.max = questionList!!.size
        setQuestionsView()

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        submitButton.setOnClickListener(this)
    }

    private fun setAlertDialog() {
        AlertDialog.Builder(this).setMessage("Do Tou want to EXIT?")
            .setPositiveButton("YES"){
            _,_ ->
            finish()
        }.setNegativeButton("No"){
            dialog,_ ->
                dialog.dismiss()
            }.show()
    }

    private fun setQuestionsView() {
        val eachQuestion = questionList!![currentQuestion - 1]

        questionTv.text = eachQuestion.question
        image.setImageResource(eachQuestion.image)
        progressBar.progress = currentQuestion
        questionCount.text = ("$currentQuestion/${questionList!!.size}").toString()
        optionOne.text = eachQuestion.optionOne
        optionTwo.text = eachQuestion.optionTwo
        optionThree.text = eachQuestion.optionThree
        submitButton.text = getString(R.string.skip)

        setDefaultOptionBackground()
    }

    private fun setDefaultOptionBackground() {
        optionOne.setBackgroundResource(R.drawable.default_optons_background)
        optionTwo.setBackgroundResource(R.drawable.default_optons_background)
        optionThree.setBackgroundResource(R.drawable.default_optons_background)
    }

    private fun setSelectedOptionBackground(view: View) {
        submitButton.text = getString(R.string.submit)
        setDefaultOptionBackground()
        view.setBackgroundResource(R.drawable.selected_option_background)
    }


    private fun addBackgroundForAnswers(selectedOption: Int, background: Int) {

        when (selectedOption) {
            1 -> optionOne.setBackgroundResource(background)
            2 -> optionTwo.setBackgroundResource(background)
            else -> optionThree.setBackgroundResource(background)
        }
    }

    private fun setTimer() {

        if (currentQuestion == questionList!!.size) {
            submitButton.text = getString(R.string.finish)
            currentQuestion++
        } else {
            timerLayout.visibility = View.VISIBLE

            object : CountDownTimer(totalTime * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeTv.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    timerLayout.visibility = View.GONE
                    selectedOption = 0
                    currentQuestion++
                    setQuestionsView()
                }
            }.start()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.opt_one -> {
                selectedOption = 1
                setSelectedOptionBackground(optionOne)
            }
            R.id.opt_two -> {
                selectedOption = 2
                setSelectedOptionBackground(optionTwo)
            }
            R.id.opt_three -> {
                selectedOption = 3
                setSelectedOptionBackground(optionThree)
            }
            R.id.btn_submit -> {
                when {
                    currentQuestion > questionList!!.size -> {
                        val intent = Intent(this, FinishActivity::class.java)
                        intent.putExtra(Constants.USERNAME, userName)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, questionList!!.size)
                        intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                        startActivity(intent)
                        finish()
                    }
                    selectedOption == 0 -> {
                        currentQuestion++
                        setQuestionsView()
                    }
                    else -> {
                        if (selectedOption == questionList!![currentQuestion - 1].correctOption) {
                            correctAnswers++
                            addBackgroundForAnswers(
                                selectedOption,
                                R.drawable.correct_answer_background
                            )
                        } else {
                            addBackgroundForAnswers(selectedOption, R.drawable.wrong_answer_background)
                            addBackgroundForAnswers(
                                questionList!![currentQuestion - 1].correctOption,
                                R.drawable.correct_answer_background
                            )
                        }
                        setTimer()
                    }
                }
            }
        }
    }

}