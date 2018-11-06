package com.example.thomasmattsson.galgeleg;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VelkomstActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velkomst);

        //FindView
        button1 = (Button) findViewById(R.id.button);

        //OnClick
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button1) {
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
        }
    }
}
