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

    Button buttonStart, buttonHighscore, buttonSettings, buttonWordList, buttonQuickstart;

    //Makes the default highscoreboard
    ArrayList<Player> playerList = new ArrayList<>();
    Player player1 = new Player("Thomas$", 50000, 62, "12/06/2017");
    Player player2 = new Player("Mikkel$", 14124, 14, "16/01/2018");
    Player player3 = new Player("Lars$", 25415, 511, "07/11/2016");
    Player player4 = new Player("Fie$", 15352, 635, "03/07/2015");
    Player player5 = new Player("Søren$", 78634, 231, "08/01/2019");
    Player player6 = new Player("Julie$", 42542, 110, "05/03/2018");
    Player player7 = new Player("Bergitte$", 78374, 90, "07/03/2016");
    Player player8 = new Player("Theodore$", 78375, 60, "30/12/2017");
    Player player9 = new Player("Alex$", 45348, 52, "12/09/2018");
    Player player10 = new Player("Petra$", 12348, 17, "24/12/2016");
    Player player11 = new Player("Børge$", 74374, 38, "23/04/2017");
    Player player12 = new Player("Kasper$", 47527, 645, "28/02/2018");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velkomst);

        //FindView
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonHighscore = (Button) findViewById(R.id.buttonHighscore);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonWordList = (Button) findViewById(R.id.buttonWordList);
        buttonQuickstart = (Button) findViewById(R.id.customStart);

        //OnClick
        buttonStart.setOnClickListener(this);
        buttonHighscore.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
        buttonWordList.setOnClickListener(this);
        buttonQuickstart.setOnClickListener(this);

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
        if (v == buttonStart) {
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
        } else if (v == buttonHighscore){
            System.out.println("Trykket på highscore");
            Intent i = new Intent(this, HighscoreActivity.class);
            startActivity(i);
        } else if (v == buttonSettings){
            System.out.println("Trykket på indstillinger");
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        } else if (v == buttonWordList){
            Intent i = new Intent(this, WordListActivity.class);
            startActivity(i);
        } else if (v == buttonQuickstart){
           BottomSheetDialog bottomSheet = new BottomSheetDialog();
           bottomSheet.show(getSupportFragmentManager(), "bottomSheet");
        }
    }

    //Save playerList to sharedPreferences
    private void saveData(ArrayList<Player> playerList) {
        this.playerList = playerList;
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Only saves the list if it doesn't exist. To reset highscore-list remove "!"
        if(!sharedPreferences.contains("score_list")) {
            Gson gson = new Gson();
            String json = gson.toJson(playerList);
            editor.putString("score_list", json);
            editor.apply();
        }
    }
}