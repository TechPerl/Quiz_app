package com.example.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.databinding.ActivitySecondPageBinding;

public class SecondPageActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySecondPageBinding binding;
    private Button falseButton;
    private Button trueButton;
    private Button nextButton;
    private Button previousButton;
    private TextView questionTextView;
    private int correct = 0;
    private int currentQuestionIndex = 0;

    private Questions[] questionBank = new Questions[]{
            new Questions(R.string.Q1,false),
            new Questions(R.string.Q2,false),
            new Questions(R.string.Q3,true),
            new Questions(R.string.Q4,true),
            new Questions(R.string.Q5,false),
            new Questions(R.string.Q6,true),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        falseButton = findViewById(R.id.FalseId);
        trueButton = findViewById(R.id.TrueId);
        nextButton = findViewById(R.id.NextId);
        previousButton = findViewById(R.id.PreviousId);

        questionTextView = findViewById(R.id.QuesId);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);


        if(currentQuestionIndex ==0){
            previousButton.setVisibility(View.INVISIBLE);
        }




//        switch (currentQuestionIndex) {
//            case 0: {
//
//                previousButton.setVisibility(View.INVISIBLE);
//                break;
//            }
//            case 6: {
//
//                nextButton.setVisibility(View.INVISIBLE);
//                break;
//            }
//
//
//            default: {
//                nextButton.setVisibility(View.VISIBLE);
//                previousButton.setVisibility(View.VISIBLE);
//            }
    }
    @SuppressLint("StringFormatInvalid")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.FalseId:
                checkAnswer(false);
                break;

            case R.id.TrueId:
                checkAnswer(true);
                break;

            case R.id.NextId:
                if(currentQuestionIndex<7){
                    currentQuestionIndex = currentQuestionIndex +1;

                    if(currentQuestionIndex==6) {

                        questionTextView.setText(getString(R.string.correct, correct));
                        nextButton.setVisibility(View.INVISIBLE);
                        previousButton.setVisibility(View.INVISIBLE);
                        trueButton.setVisibility(View.INVISIBLE);
                        falseButton.setVisibility(View.INVISIBLE);

                    }else if(currentQuestionIndex>0) {
                        previousButton.setVisibility(View.VISIBLE);
                        updateQuestion();
                    }
                }
                break;


            case R.id.PreviousId:
                if(currentQuestionIndex > 0){
                    currentQuestionIndex = (currentQuestionIndex -1 )%questionBank.length;

                    if(currentQuestionIndex ==0){
                        previousButton.setVisibility(View.INVISIBLE);
                        updateQuestion();
                    }
                }
           }

       }

    private void updateQuestion()
    {
        //log.d("current","onClick" +currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());

    }


    private void checkAnswer(boolean falseButton) {
        boolean answerTrue = questionBank[currentQuestionIndex].isAnswerTrue();

        int toastMessageId;

        if(falseButton==answerTrue){
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else{
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(SecondPageActivity.this,toastMessageId,Toast.LENGTH_SHORT).show();
    }
}