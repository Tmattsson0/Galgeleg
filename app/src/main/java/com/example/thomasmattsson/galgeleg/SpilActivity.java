package com.example.thomasmattsson.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import SpilLogik.GalgeLogik;

public class SpilActivity extends AppCompatActivity implements View.OnClickListener {

    GalgeLogik logik = new GalgeLogik();
    Button button2;
    TextView GætteTekst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        //FindView
        GætteTekst = (TextView) findViewById(R.id.textView5);
        button2 = (Button) findViewById(R.id.button2);

        //OnClick
        button2.setOnClickListener(this);
        GætteTekst.setOnClickListener(this);

        //Random word
        Random rn = new Random();
        int Rand = rn.nextInt(logik.muligeOrd.size());
        String ordet = logik.muligeOrd.get(Rand);

        //Obscure word method
        GætteTekst.setText(logik.wordToStarConverter(ordet));
        Log.d(ordet,"onCreate: Ordet er: " + ordet);
    }

    @Override
    public void onClick(View v) {


    }
}
