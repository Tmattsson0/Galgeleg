package com.example.thomasmattsson.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EnterName;
    TextView GameWon, whatWord, score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        String ordet = getIntent().getExtras().getString("ordet");

        EnterName = (EditText) findViewById(R.id.editTextName);
        GameWon = (TextView) findViewById(R.id.textViewWon);
        whatWord = (TextView) findViewById(R.id.textViewWhatWord);
        score = (TextView) findViewById(R.id.textViewScore);

        EnterName.setOnClickListener(this);

        whatWord.setText("Ordet var: "+ordet);
    }

    @Override
    public void onClick(View v) {

    }
}
