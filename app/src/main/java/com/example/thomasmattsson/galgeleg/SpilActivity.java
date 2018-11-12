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

        //OnClick
//        gætteTekst.setOnClickListener(this);
//        forkerteBogstaver.setOnClickListener(this);

        //Obscure word method
        gætteTekst.setText(logik.getSynligtOrd());
        System.out.println("onCreate: Ordet er: " + logik.getOrdet());
    }

    @Override
    public void onClick(View v) {
        //for now, empty.
    }

    //Button is pressed with method defined in XML
    public void onBtnClicked(View v) {
        letterButton = findViewById(v.getId());
        letterButtonPressed(letterButton);
        count++;
    }

    public void letterButtonPressed(Button button) {
        //Set a string equal to the getText() from the button.
        String BogstavGæt = (button.getText().toString().toLowerCase());
//        String ordet = "ordet";

        //Calls to the logic.
        logik.gætBogstav(BogstavGæt);
        forkerteBogstaver.setText("Forkerte bogstaver ["
                + logik.getAntalForkerteBogstaver()
                + " ud af 7]: \n"
                + logik.wrongLetters(logik.getBrugteBogstaver().toString(), logik.getOrdet()));
        gætteTekst.setText(logik.getSynligtOrd());
        button.setVisibility(View.GONE);
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
//            Toast.makeText(this, "Du vandt!", Toast.LENGTH_SHORT).show();
//            String transferCount = "transferCount";
            Intent i = new Intent(this, WonActivity.class);
            i.putExtra("ordet", logik.getOrdet());
            i.putExtra("transferCount", count);
            startActivity(i);
            finish();
        } else if (logik.isSpilletErTabt()){
            Intent i = new Intent(this, LostActivity.class);
            i.putExtra("ordet", logik.getOrdet());
            startActivity(i);
            finish();
        }
    }
}
