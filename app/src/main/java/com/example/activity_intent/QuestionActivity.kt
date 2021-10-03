package com.example.activity_intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.activity_intent.Constants.ANSWER_TEXT
import com.example.activity_intent.Constants.EMPTY_STRING
import com.example.activity_intent.Constants.QUESTION_TEXT

class QuestionActivity : AppCompatActivity() {

    private lateinit var question: EditText
    private lateinit var submit: Button
    private lateinit var lastAnswer: TextView

    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                val data = result.data
                lastAnswer.text = data?.getStringExtra(ANSWER_TEXT) ?: EMPTY_STRING
            }
            RESULT_CANCELED -> {
                lastAnswer.text = getString(R.string.operation_cancelled)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        question = findViewById(R.id.questionEditText)
        lastAnswer = findViewById(R.id.lastAnswerTextView)
        submit = findViewById(R.id.submitButton)

        submit.setOnClickListener {
            val intent = Intent(this@QuestionActivity, AnswerActivity::class.java)
            intent.putExtra(QUESTION_TEXT, question.text.toString())
            someActivityResultLauncher.launch(intent)
        }
    }

}