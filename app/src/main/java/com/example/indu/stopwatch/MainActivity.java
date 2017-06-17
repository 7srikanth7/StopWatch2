package com.example.indu.stopwatch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView textView ;
    Button stopbutton;
    Button button ;
    ToggleButton toggle;
    Button restartbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =  (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
        stopbutton = (Button)findViewById(R.id.stopbutton);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        restartbutton = (Button)findViewById(R.id.restartbutton);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                new CountDownTimer(30000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        textView.setText("00 : "+ millisUntilFinished / 1000);
                       // button.setText("Running..");
                        button.setEnabled(false);
                        stopbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cancel();
                               // button.setText("Start");
                                button.setEnabled(true);
                            }
                        });

                    }
                    public void onFinish() {
                        boolean on = toggle.isChecked();
                        if(on)
                        {
                            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                            mp.start();
                        }
                        textView.setText("Done!");
                        button.setText("Start");

                    }
                }.start();
            }
       });

        restartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.super.onStart();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //this will always start your activity as a new task
                startActivity(intent);


               /* Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                onStop();
                startActivity(i);*/

            }
        });


    }


}






