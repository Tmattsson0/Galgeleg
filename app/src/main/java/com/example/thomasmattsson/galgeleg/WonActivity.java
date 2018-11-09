package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    TextView GameWon, whatWord, score;
    Button tryAgain, menuButton, saveScore;

    //Random score
    Random r = new Random();
    int low = 1000;
    int high = 10000;
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

        EnterName = (EditText) findViewById(R.id.editTextName);
        GameWon = (TextView) findViewById(R.id.textViewWon);
        whatWord = (TextView) findViewById(R.id.textViewWhatWord);
        score = (TextView) findViewById(R.id.textViewScore);
        tryAgain = (Button) findViewById(R.id.tryAgain);
        menuButton = (Button) findViewById(R.id.backToMenu);
        saveScore = (Button) findViewById(R.id.saveHighScore);

        tryAgain.setOnClickListener(this);
        EnterName.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        saveScore.setOnClickListener(this);

        whatWord.setText("Ordet var: " + ordet);
        score.setText("Score: " + Integer.toString(result) + " pt.");
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
        } else if (v == saveScore){
            EnterName.getText();
            Player p = new Player(EnterName.getText().toString(), result);
            userScores.add(p);

            System.out.println(EnterName.getText());
            saveData();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userScores);
        editor.putString("score_list", json);
        editor.apply();
    }

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
