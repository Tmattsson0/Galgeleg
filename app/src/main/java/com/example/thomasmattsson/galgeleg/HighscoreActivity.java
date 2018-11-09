package com.example.thomasmattsson.galgeleg;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    ArrayList<Player> playerList;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        playerList = loadData();
        Collections.sort(playerList, comparing(Player::getScore).reversed());

        mRecyclerView = (RecyclerView) findViewById(R.id.simple_recyclerview);
        RecyclerView.LayoutManager mLayoutManager;
        RecyclerView.Adapter mAdapter;
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SimpleAdapter(playerList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);




    }

    private ArrayList<Player> loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("score_list", null);
        Type type = new TypeToken<ArrayList<Player>>() {}.getType();
        playerList = gson.fromJson(json, type);

        if (playerList == null){
            playerList = new ArrayList<>();
        }
        return playerList;
    }
}