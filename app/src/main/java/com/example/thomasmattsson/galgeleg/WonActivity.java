package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EnterName;
    TextView GameWon, whatWord, score;
    Button tryAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        String ordet = getIntent().getExtras().getString("ordet");

        EnterName = (EditText) findViewById(R.id.editTextName);
        GameWon = (TextView) findViewById(R.id.textViewWon);
        whatWord = (TextView) findViewById(R.id.textViewWhatWord);
        score = (TextView) findViewById(R.id.textViewScore);
        tryAgain = (Button) findViewById(R.id.tryAgain);

        tryAgain.setOnClickListener(this);
        EnterName.setOnClickListener(this);

        whatWord.setText("Ordet var: " + ordet);
    }

    @Override
    public void onClick(View v) {
        if (v == tryAgain) {
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
            finish();
        }
    }
}
