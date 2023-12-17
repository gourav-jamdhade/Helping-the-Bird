package com.example.helpthebird;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView bird, enemy1, enemy2, enemy3, coin, volume;

    Button buttonStart;

    private Animation animation;

    boolean status = false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bird = findViewById(R.id.bird);
        enemy1 = findViewById(R.id.enemy1);
        enemy2 = findViewById(R.id.enemy2);
        enemy3 = findViewById(R.id.enemy3);
        coin = findViewById(R.id.coin);
        volume = findViewById(R.id.volume);

        buttonStart = findViewById(R.id.buttonStart);

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_anim);
        bird.setAnimation(animation);
        enemy1.setAnimation(animation);
        enemy2.setAnimation(animation);
        enemy3.setAnimation(animation);
        coin.setAnimation(animation);


    }


    @Override
    protected void onResume() {
        Log.v("On Resume", "On Resume");
        super.onResume();

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.game_audio);
        mediaPlayer.start();

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!status) {
                    mediaPlayer.setVolume(0, 0);
                    volume.setImageResource(R.drawable.baseline_volume_off_24);
                    status = true;
                } else {
                    mediaPlayer.setVolume(1, 1);
                    volume.setImageResource(R.drawable.baseline_volume_up_24);
                    status = false;
                }
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                volume.setImageResource(R.drawable.baseline_volume_up_24);
                Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        Log.v("On Restart", "On Restart");
        super.onRestart();


        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!status) {
                    mediaPlayer.setVolume(0, 0);
                    volume.setImageResource(R.drawable.baseline_volume_off_24);
                    status = true;
                } else {
                    mediaPlayer.setVolume(1, 1);
                    volume.setImageResource(R.drawable.baseline_volume_up_24);
                    status = false;
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.setVolume(0, 0);
    }
}