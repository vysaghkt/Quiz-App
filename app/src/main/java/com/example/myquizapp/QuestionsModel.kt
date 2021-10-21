package com.example.myquizapp

data class QuestionsModel(
    val questionNumber: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val correctOption: Int
)