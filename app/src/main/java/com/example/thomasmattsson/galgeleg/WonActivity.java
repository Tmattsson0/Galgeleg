package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

import Data.Player;

public class WonActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Player> userScores;

    EditText EnterName;
    TextView GameWon, whatWord, score, howManyTries;
    Button tryAgain, menuButton, saveScore;

    MediaPlayer mp;

    //Random score
    //TODO Implement real scoring system
    Random r = new Random();
    int low = 10000;
    int high = 100000;
    int result = r.nextInt(high-low) + low;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        userScores = loadData();

        // Print the name from the list....
        for(Player p : userScores) {
            System.out.println(p.getName());
        }

        //Getting word from previous activity
        String ordet = getIntent().getExtras().getString("ordet");
        int count = getIntent().getExtras().getInt("transferCount");

        EnterName = (EditText) findViewById(R.id.editTextName);
        GameWon = (TextView) findViewById(R.id.textViewWon);
        whatWord = (TextView) findViewById(R.id.textViewWhatWord);
        score = (TextView) findViewById(R.id.textViewScore);
        howManyTries = (TextView) findViewById(R.id.howManyTries);
        tryAgain = (Button) findViewById(R.id.tryAgain);
        menuButton = (Button) findViewById(R.id.backToMenu);
        saveScore = (Button) findViewById(R.id.saveHighScore);

        tryAgain.setOnClickListener(this);
        EnterName.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        saveScore.setOnClickListener(this);

        whatWord.setText("Ordet var: " + ordet);
        score.setText("Score: " + Integer.toString(result) + " pt.");
        howManyTries.setText("Antal forsøg: " + Integer.toString(count));

        mp = MediaPlayer.create(this, R.raw.cheering);
        mp.start();

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
            //Todo: Make date and time real.
        } else if (v == saveScore && !EnterName.getText().toString().equals("")){
            EnterName.getText();
            Player p = new Player(EnterName.getText().toString(), result, 111, "23/04/96");
            userScores.add(p);
            System.out.println(EnterName.getText());
            saveData();
            //Disable button so you can't double entry
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

    //Load the current list to manipulate as arraylist in java.
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
