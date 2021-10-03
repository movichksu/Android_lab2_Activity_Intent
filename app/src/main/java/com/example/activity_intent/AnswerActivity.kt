package com.example.activity_intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.activity_intent.Constants.ANSWER_TEXT
import com.example.activity_intent.Constants.EMPTY_STRING
import com.example.activity_intent.Constants.QUESTION_TEXT

class AnswerActivity : AppCompatActivity() {

    private lateinit var question: TextView
    private lateinit var answer: EditText
    private lateinit var submit: Button
    private lateinit var cancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        question = findViewById(R.id.questionTextView)
        answer = findViewById(R.id.answerEditText)
        submit = findViewById(R.id.submitButton)
        cancel = findViewById(R.id.cancelButton)

        question.text = intent.extras?.getString(QUESTION_TEXT) ?: EMPTY_STRING

        submit.setOnClickListener {
            val intent = Intent()
            intent.putExtra(ANSWER_TEXT, answer.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

        cancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

}