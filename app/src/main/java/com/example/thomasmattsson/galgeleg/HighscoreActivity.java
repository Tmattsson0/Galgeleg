package com.example.thomasmattsson.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import Data.Player;

import static java.util.Comparator.comparing;

//https://www.youtube.com/watch?v=Q2l1Um-cXBQ

public class HighscoreActivity extends AppCompatActivity {

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

    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        mRecyclerView = (RecyclerView) findViewById(R.id.simple_recyclerview);
        RecyclerView.LayoutManager mLayoutManager;
        RecyclerView.Adapter mAdapter;
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SimpleAdapter(playerList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Clear list
        playerList.clear();

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


    }
}