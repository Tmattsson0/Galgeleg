package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LostActivity extends AppCompatActivity implements View.OnClickListener {

    TextView GameLost, whatWord;
    Button tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        String ordet = getIntent().getExtras().getString("ordet");

        GameLost = (TextView) findViewById(R.id.textViewLost);
        whatWord = (TextView) findViewById(R.id.textViewWhatWord);
        tryAgain = (Button) findViewById(R.id.tryAgain);

        tryAgain.setOnClickListener(this);

        whatWord.setText("Ordet var: "+ordet);
    }

    @Override
    public void onClick(View v) {
        if (v == tryAgain){
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
        }
    }
}
