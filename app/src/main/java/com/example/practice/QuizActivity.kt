package com.example.practice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var list: ArrayList<Quizmodel> = ArrayList()
    private var count = 0
    private var score = 0
    private var selectedAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Add Questions
        list.add(
            Quizmodel(
                "Who is the PM of India?",
                "Modi Ji",
                "Rahul Ji",
                "Keju Ji",
                "Naman",
                "Modi Ji"
            )
        )
        list.add(
            Quizmodel("How many states are there in India?", "26", "29", "28", "27", "28")
        )
        list.add(
            Quizmodel(
                "Who won the cricket WC 2024?",
                "New Zealand",
                "India",
                "England",
                "Australia",
                "Australia"
            )
        )

        // Load first question
        loadQuestion()

        // Handle option selection
        binding.option1.setOnClickListener { selectedAnswer = binding.option1.text.toString() }
        binding.option2.setOnClickListener { selectedAnswer = binding.option2.text.toString() }
        binding.option3.setOnClickListener { selectedAnswer = binding.option3.text.toString() }
        binding.option4.setOnClickListener { selectedAnswer = binding.option4.text.toString() }

        // Handle next button
        binding.nextbutton.setOnClickListener {
            if (selectedAnswer == null) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            } else {
                checkAnswerAndMoveOn()
            }
        }
    }

    private fun loadQuestion() {
        val q = list[count]
        binding.Question.text = q.Question
        binding.option1.text = q.option1
        binding.option2.text = q.option2
        binding.option3.text = q.option3
        binding.option4.text = q.option4
        selectedAnswer = null
    }

    private fun checkAnswerAndMoveOn() {
        if (selectedAnswer == list[count].ans) {
            score++
        }
        count++
        if (count >= list.size) {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        } else {
            loadQuestion()
        }
    }
}
