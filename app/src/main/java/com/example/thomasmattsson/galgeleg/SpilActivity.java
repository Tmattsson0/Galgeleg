package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import SpilLogik.GalgeLogik;

public class SpilActivity extends AppCompatActivity implements View.OnClickListener {

    GalgeLogik logik = new GalgeLogik();

    Button letterButton;
    TextView gætteTekst, forkerteBogstaver;
    GridLayout letterGrid;
    ImageView gallow;

    long startTime, endTime;
    int totalTime, score;

    int count = 0;

    public SpilActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        //FindView
        gætteTekst = (TextView) findViewById(R.id.textView5);
        forkerteBogstaver = (TextView) findViewById(R.id.textView);
        letterGrid = findViewById(R.id.gridLayout);
        gallow = findViewById(R.id.gallow);
        gallow.setVisibility(gallow.INVISIBLE);

        //Obscure word method
        gætteTekst.setText(logik.getSynligtOrd());
        System.out.println("onCreate: Ordet er: " + logik.getOrdet());
        //Start timer
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onClick(View v) {
        //for now, empty.
    }

    //letterButton is pressed with method defined in XML
    public void onBtnClicked(View v) {
        letterButton = findViewById(v.getId());
        letterButtonPressed(letterButton);
        count++;
    }

    public void letterButtonPressed(Button button) {
        //Set a string equal to the getText() from the button.
        String BogstavGæt = (button.getText().toString().toLowerCase());

        //Calls to the logic.
        logik.gætBogstav(BogstavGæt);
        forkerteBogstaver.setText("Forkerte bogstaver ["
                + logik.getAntalForkerteBogstaver()
                + " ud af 7]: \n"
                + logik.wrongLetters(logik.getBrugteBogstaver().toString(), logik.getOrdet()));
        gætteTekst.setText(logik.getSynligtOrd());
        button.setVisibility(View.GONE);

        //Update the gallow and stick man
        gallow.setVisibility(gallow.VISIBLE);

        switch (logik.getAntalForkerteBogstaver()){
            case 1:
                gallow.setImageResource(R.mipmap.ic_gallow_empty);
                break;
            case 2:
                gallow.setImageResource(R.mipmap.ic_gallow_empty_1);
                break;
            case 3:
                gallow.setImageResource(R.mipmap.ic_gallow_empty_2);
                break;
            case 4:
                gallow.setImageResource(R.mipmap.ic_gallow_empty_3);
                break;
            case 5:
                gallow.setImageResource(R.mipmap.ic_gallow_empty_4);
                break;
            case 6:
                gallow.setImageResource(R.mipmap.ic_gallow_empty_5);
                break;
            case 7:
                gallow.setImageResource(R.mipmap.ic_gallow_empty_6);
                break;
            default:
                gallow.setImageResource(R.mipmap.ic_gallow_empty);
                gallow.setVisibility(gallow.INVISIBLE);
                break;
        }

        //Current end-game
        logik.logStatus();
        if (logik.isSpilletErVundet()) {
            endTime = System.currentTimeMillis();
            totalTime = Math.round(((endTime - startTime)/1000));
            System.out.println("Det tog " + (totalTime) + " sekunder");

            //Calculate score
            int letterPenalty = logik.getAntalForkerteBogstaver()*5000;
            System.out.println("letterPenalty: " + letterPenalty);
            int timePenalty = (totalTime*10000)/logik.getOrdet().length();
            System.out.println("timePenalty: " + timePenalty);
            score = (100000 - letterPenalty - timePenalty);
            System.out.println("Scoren er: " + score);
            if (score < 0) {
                score = 0;
                score += logik.getOrdet().length()*1000;
            }

            //Send data to wonActivity
            Intent i = new Intent(this, WonActivity.class);
            i.putExtra("ordet", logik.getOrdet());
            i.putExtra("transferCount", count);
            i.putExtra("time", totalTime);
            i.putExtra("score", score);
            startActivity(i);
            finish();
        } else if (logik.isSpilletErTabt()){
            //Send data to lostActivity
            Intent i = new Intent(this, LostActivity.class);
            i.putExtra("ordet", logik.getOrdet());
            startActivity(i);
            finish();
        }
    }
}
