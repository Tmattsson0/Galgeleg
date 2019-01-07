package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class LostActivity extends AppCompatActivity implements View.OnClickListener {

    TextView GameLost, whatWord;
    Button tryAgain, menuButton;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        //Get the word from previous activity
        String ordet = getIntent().getExtras().getString("ordet");

        GameLost = (TextView) findViewById(R.id.textViewLost);
        whatWord = (TextView) findViewById(R.id.textViewWhatWord);
        tryAgain = (Button) findViewById(R.id.tryAgain);
        menuButton = (Button) findViewById(R.id.backToMenu);

        tryAgain.setOnClickListener(this);
        menuButton.setOnClickListener(this);

        whatWord.setText("Ordet var: "+ordet);

        mp = MediaPlayer.create(this, R.raw.sad_trombone);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == tryAgain){
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
            finish();
        } else if (v == menuButton){
            Intent i = new Intent(this,VelkomstActivity.class);
            startActivity(i);
            finish();
        }
    }
}
