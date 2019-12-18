package com.ryanschefske.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true)
    };

    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        trueButton.setOnClickListener(new TrueButtonClickListener());
        falseButton = findViewById(R.id.false_button);
        falseButton.setOnClickListener(new FalseButtonClickListener());
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new NextButtonClickListener());
        questionTextView = findViewById(R.id.question_text_view);
        updateQuestion();
    }

    private void updateQuestion() {
        int question = questions[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(Boolean userPress) {
        boolean correctAnswer = questions[currentIndex].isAnswerTrue();
        int toastTextId;
        if(userPress == correctAnswer) {
            toastTextId = R.string.correct;
        } else {
            toastTextId = R.string.incorrect;
        }

        Toast.makeText(this, toastTextId, Toast.LENGTH_SHORT).show();
    }

    private class TrueButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            checkAnswer(true);
        }
    }

    private class FalseButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            checkAnswer(false);
        }
    }

    private class NextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentIndex = (currentIndex + 1) % questions.length;
            updateQuestion();
        }
    }

}

