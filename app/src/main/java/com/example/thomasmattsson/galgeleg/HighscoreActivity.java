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

public class HighscoreActivity extends AppCompatActivity {

    ArrayList<Player> playerList = new ArrayList<Player>();

    Player player1 = new Player("Thomas", 50000);
    Player player2 = new Player("Mikkel", 14124);
    Player player3 = new Player("Lars", 25415);
    Player player4 = new Player("Fie", 15352);
    Player player5 = new Player("Lukas", 40000);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        SimpleAdapter adapter = new SimpleAdapter(generateSimpleList());
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.simple_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private List<SimpleViewModel> generateSimpleList() {
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        playerList.add(player5);
        Collections.sort(playerList, comparing(Player::getScore).reversed());

        System.out.println(playerList.get(0).getName());

        List<SimpleViewModel> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            simpleViewModelList.add(new SimpleViewModel(playerList.get(i).getName(), playerList.get(i).getScore()));
        }

        return simpleViewModelList;
    }
}