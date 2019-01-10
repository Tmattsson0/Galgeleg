package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plattysoft.leonids.ParticleSystem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Data.Player;

public class WonActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Player> userScores;

    EditText EnterName;
    TextView GameWon, whatWord, scoreView, howManyTries, timeSpent;
    Button tryAgain, menuButton, saveScore;

    int time, score;
    String date;

    MediaPlayer mp;

    String pattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        userScores = loadData();
        //Getting today's dat
        date = simpleDateFormat.format(new Date());
        System.out.println(date);
        // Print the name from the list....
        for(Player p : userScores) {
            System.out.println(p.getName());
        }

        //Getting data from previous activity
        String ordet = getIntent().getExtras().getString("ordet");
        int count = getIntent().getExtras().getInt("transferCount");
        time = getIntent().getExtras().getInt("time");
        score = getIntent().getExtras().getInt("score");

        EnterName = (EditText) findViewById(R.id.editTextName);
        GameWon = (TextView) findViewById(R.id.textViewWon);
        whatWord = (TextView) findViewById(R.id.textViewWhatWord);
        scoreView = (TextView) findViewById(R.id.textViewScore);
        howManyTries = (TextView) findViewById(R.id.howManyTries);
        timeSpent = (TextView) findViewById(R.id.timeSpent);
        tryAgain = (Button) findViewById(R.id.tryAgain);
        menuButton = (Button) findViewById(R.id.backToMenu);
        saveScore = (Button) findViewById(R.id.saveHighScore);

        tryAgain.setOnClickListener(this);
        EnterName.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        saveScore.setOnClickListener(this);

        whatWord.setText("Ordet var: " + ordet);
        scoreView.setText("Score: " + Integer.toString(score) + " pt.");
        howManyTries.setText("Antal forsøg: " + Integer.toString(count));
        timeSpent.setText("Du brugte " + time + " sekunder" );

        //Start music
        mp = MediaPlayer.create(this, R.raw.cheering);
        mp.start();

        //Confetti from 3rd party
        new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90)
                .emit(findViewById(R.id.emiter_top_right), 8);

        new ParticleSystem(this, 80, R.drawable.confeti3, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90)
                .emit(findViewById(R.id.emiter_top_left), 8);

        //End music when done
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            }
        });
    }



    @Override
    public void onClick(View v) {
        if (v == tryAgain) {
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
            finish();
        } else if (v == menuButton){
            Intent i = new Intent(this, VelkomstActivity.class);
            startActivity(i);
            finish();
        } else if (v == saveScore && !EnterName.getText().toString().equals("")){
            //Hide keyboard
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            //Adds new highscore to list and saves in shared preferences
            EnterName.getText();
            Player p = new Player(EnterName.getText().toString(), score, time, date);
            userScores.add(p);
            System.out.println(EnterName.getText());
            saveData();

            //Disable save button so you can't make double entry
            saveScore.setClickable(false);
        }
    }

    //Save the new list to sharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userScores);
        editor.putString("score_list", json);
        editor.apply();
    }

    //Load the current list to manipulate as arrayList in java.
    private ArrayList<Player> loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("score_list", null);
        Type type = new TypeToken<ArrayList<Player>>() {}.getType();
        userScores = gson.fromJson(json, type);

        if (userScores == null){
            userScores = new ArrayList<>();
        }
        return userScores;
    }
}
