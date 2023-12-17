package com.example.helpthebird;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewResultInfo, textViewHighestScore, textViewYourScore;
    private Button buttonPlayAgain;
    private SharedPreferences sharedPreferences;

    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResultInfo = findViewById(R.id.textViewResultInfo);
        textViewYourScore = findViewById(R.id.textViewYourScore);
        textViewHighestScore = findViewById(R.id.textViewHighestScore);
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);

        score = getIntent().getIntExtra("score",0);

        textViewYourScore.setText("Your Score : " + score);

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore",0);

        if(score >= 200){
            textViewResultInfo.setText("You won the Game😊");
            textViewHighestScore.setText("Highest Score : " + score );
            sharedPreferences.edit().putInt("highestScore",score).apply();
        }

        else if(score >=highestScore){

            textViewResultInfo.setText("Sorry, you lost the game😢");

            textViewHighestScore.setText("Highest Score : " + score );
            sharedPreferences.edit().putInt("highestScore",score).apply();
        }

        else{

            textViewResultInfo.setText("Sorry, you lost the game😢");
            textViewHighestScore.setText("Highest Score : " + score );

        }

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }

    @SuppressLint("MissingSuperCall")
    @Override

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(ResultActivity.this,R.style.AlertDialogButtons));
        builder.setTitle("Help The Innocent Bird");
        builder.setMessage("Are you sure to exit?");
        builder.setCancelable(false);
        builder.setNegativeButton("Quit game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.create().show();

    }
}