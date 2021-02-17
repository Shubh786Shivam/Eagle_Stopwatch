package com.example.timerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView6;
    SeekBar seekBar;
    Button button2;
    CountDownTimer countDownTimer;
    boolean flag = false;
    public void resetTimer(){
        textView6.setText("00:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button2.setText("START!");
        flag = false;
    }
    public void buttonPressed(View view){
        if(flag){
             resetTimer();
        }
        else {
            flag = true;
            seekBar.setEnabled(false);
            button2.setText("STOP");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.finish_alarm);
                    mp.start();
                    resetTimer();

                }

            }.start();
        }
    }
    public void updateTimer(int progress){
        int min = progress/60;
        int sec = progress - (min*60);
        String strMin = Integer.toString(min);
        String strSec = Integer.toString(sec);
        if(strMin.length() == 1){
            strMin = "0" + strMin;
        }
        if(strSec.length() == 1){
            strSec = "0" + strSec;
        }
        textView6.setText(strMin + ":" + strSec);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView6 = findViewById(R.id.textView6);
        button2 = findViewById(R.id.button2);
        seekBar.setProgress(30);
        seekBar.setMax(600);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
