package com.example.asus.timerdemo;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textTimer = findViewById(R.id.timer);
    SeekBar seekBar = findViewById(R.id.seekBar);
    Button button = findViewById(R.id.clickButton);
    CountDownTimer countDownTimer;
    int counterIsActive = 0;
    public void buttonClicked(View view) {
        if (counterIsActive == 1){
            counterIsActive = 0;
            seekBar.setProgress(130);
            textTimer.setText("2:10");
            button.setText("START");
            countDownTimer.cancel();
        }else {
            button.setText("STOP");
            final int time = seekBar.getProgress();
            countDownTimer = new CountDownTimer(time * 1000, 1000) {
                int tempSecond = time;
                int tempMinute = time;
                int seconds;
                int minutes;
                int length;


                public void onTick(long millisecondUntilDone) {
                    minutes = (int) tempMinute / 60;
                    seconds = (int) tempSecond % 60;
                    length = String.valueOf(seconds).length();

                    if (length == 1) {
                        textTimer.setText(minutes + ":0" + seconds);
                    } else {
                        textTimer.setText(minutes + ":" + seconds);
                    }
                    tempSecond--;
                    tempMinute--;
                }

                public void onFinish() {
                    textTimer.setText("DONE CUKK");
                    cancel();
                }
            };
            countDownTimer.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar.setMax(720);
        seekBar.setProgress(130);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressSeconds;
            int progressMinutes;
            int length;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progressMinutes = progress / 60;
                progressSeconds = progress % 60;
                length = String.valueOf(progressSeconds).length();
                if (length == 1) {
                    textTimer.setText(progressMinutes + ":0" + progressSeconds);
                } else {
                    textTimer.setText(progressMinutes + ":" + progressSeconds);
                }

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
