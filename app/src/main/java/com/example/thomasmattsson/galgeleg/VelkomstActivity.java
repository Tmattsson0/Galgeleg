package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import Data.Player;

import static java.util.Comparator.comparing;

public class VelkomstActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3;

    //Makes the default highscoreboard
    ArrayList<Player> playerList = new ArrayList<>();
    Player player1 = new Player("Thomas", 50000);
    Player player2 = new Player("Mikkel", 14124);
    Player player3 = new Player("Lars", 25415);
    Player player4 = new Player("Fie", 15352);
    Player player5 = new Player("Søren", 78634);
    Player player6 = new Player("Julie", 42542);
    Player player7 = new Player("Bergitte", 78374);
    Player player8 = new Player("Theodore", 78375);
    Player player9 = new Player("Alex", 45348);
    Player player10 = new Player("Petra", 12348);
    Player player11 = new Player("Børge", 74374);
    Player player12 = new Player("Kasper", 47527);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velkomst);

        //FindView
        button1 = (Button) findViewById(R.id.buttonStart);
        button2 = (Button) findViewById(R.id.buttonHighscore);
        button3 = (Button) findViewById(R.id.buttonSettings);

        //OnClick
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        //Clear list
        playerList.clear();

        //Add players
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        playerList.add(player5);
        playerList.add(player6);
        playerList.add(player7);
        playerList.add(player8);
        playerList.add(player9);
        playerList.add(player10);
        playerList.add(player11);
        playerList.add(player12);
        Collections.sort(playerList, comparing(Player::getScore).reversed());

        //Save to shared preferences
        saveData(playerList);
    }

    @Override
    public void onClick(View v) {
        if (v == button1) {
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
        } else if (v == button2){
            System.out.println("Trykket på highscore");
            Intent i = new Intent(this, HighscoreActivity.class);
            startActivity(i);
        } else if (v == button3){
            System.out.println("Trykket på indstillinger");
        }
    }

    private void saveData(ArrayList<Player> playerList) {
        this.playerList = playerList;
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Only saves the list if it doesn't exist
        if(!sharedPreferences.contains("score_list")) {
            Gson gson = new Gson();
            String json = gson.toJson(playerList);
            editor.putString("score_list", json);
            editor.apply();
        }
    }
}
