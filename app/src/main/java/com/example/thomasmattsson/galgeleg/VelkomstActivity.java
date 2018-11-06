package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VelkomstActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3;

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
}
