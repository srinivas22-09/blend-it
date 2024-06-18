package com.example.nammakarnataka.NewQuiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nammakarnataka.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewQuiz extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    List<Integer> questionOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);

//        totalQuestionsTextView=findViewById(R.id.total_questions);
        questionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submitBtn=findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

//        totalQuestionsTextView.setText("Total questions : " + totalQuestion);
        questionOrder = new ArrayList<>();
        for (int i = 0; i < totalQuestion; i++) {
            questionOrder.add(i);
        }
        Collections.shuffle(questionOrder);

        loadNewQuestion();

        findViewById(R.id.back_quiz).setOnClickListener((v)->{
            finish();
        });
 }


    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[questionOrder.get(currentQuestionIndex)])) {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }
    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        int shuffledIndex = questionOrder.get(currentQuestionIndex);
        questionTextView.setText(QuestionAnswer.question[shuffledIndex]);
        ansA.setText(QuestionAnswer.choices[shuffledIndex][0]);
        ansB.setText(QuestionAnswer.choices[shuffledIndex][1]);
        ansC.setText(QuestionAnswer.choices[shuffledIndex][2]);
        ansD.setText(QuestionAnswer.choices[shuffledIndex][3]);
    }
    void finishQuiz() {
        String passStatus = (score > totalQuestion * 0.60) ? "Passed" : "Failed";

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        Collections.shuffle(questionOrder); // Shuffle questions for a new quiz
        loadNewQuestion();
    }
}