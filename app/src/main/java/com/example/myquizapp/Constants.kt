package com.example.myquizapp

class Constants {
    companion object {

        const val USERNAME = "username"
        const val CORRECT_ANSWERS = "correct_answers"
        const val TOTAL_QUESTIONS = "total_questions"

        fun getQuestionList(): ArrayList<QuestionsModel> {
            val list: ArrayList<QuestionsModel> = arrayListOf()

            list.add(
                QuestionsModel(
                    1,
                    "Name The Flag",
                    R.drawable.argentina,
                    "Argentina",
                    "Armania",
                    "Brazil",
                    1
                )
            )
            list.add(
                QuestionsModel(
                    2,
                    "Name The Flag",
                    R.drawable.andorra,
                    "Sweden",
                    "Andorra",
                    "Austria",
                    2
                )
            )
            list.add(
                QuestionsModel(
                    3,
                    "Name The Flag",
                    R.drawable.angola,
                    "Angola",
                    "China",
                    "Austria",
                    1
                )
            )
            list.add(
                QuestionsModel(
                    4,
                    "Name The Flag",
                    R.drawable.armania,
                    "Japan",
                    "Germany",
                    "Armania",
                    3
                )
            )
            list.add(
                QuestionsModel(
                    5,
                    "Name The Flag",
                    R.drawable.austria,
                    "Argentina",
                    "Austria",
                    "Brazil",
                    2
                )
            )

            return list
        }
    }
}