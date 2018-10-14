package com.example.thomasmattsson.galgeleg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import SpilLogik.GalgeLogik;

public class SpilActivity extends AppCompatActivity implements View.OnClickListener {

    GalgeLogik logik = new GalgeLogik();
    Button button2;
    TextView gætteTekst, forkerteBogstaver;
    EditText gætteBogstav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        //FindView
        gætteTekst = (TextView) findViewById(R.id.textView5);
        button2 = (Button) findViewById(R.id.button2);
        gætteBogstav = (EditText) findViewById(R.id.editText);
        forkerteBogstaver = (TextView) findViewById(R.id.textView);

        //OnClick
        button2.setOnClickListener(this);
        gætteTekst.setOnClickListener(this);
        gætteBogstav.setOnClickListener(this);
        forkerteBogstaver.setOnClickListener(this);

        //Random word
        Random rn = new Random();
        int Rand = rn.nextInt(logik.muligeOrd.size());
        String ordet = logik.muligeOrd.get(Rand);

        //Obscure word method
        gætteTekst.setText(logik.getSynligtOrd());
        Log.d(ordet, "onCreate: Ordet er: " + ordet);
    }

    @Override
    public void onClick(View v) {


        //Gæt knappen
        if (v == button2) { //(event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            String BogstavGæt = (gætteBogstav.getText().toString());
            if (TextUtils.isEmpty(BogstavGæt)) {
                Toast.makeText(this, "Indtast venligst et bogstav", Toast.LENGTH_SHORT).show();
            }
            logik.gætBogstav(BogstavGæt);
            forkerteBogstaver.setText("Forkerte bogstaver [" + logik.getAntalForkerteBogstaver() + "]: \n" + logik.wrongLetters(logik.getBrugteBogstaver().toString(), logik.getOrdet()));
            gætteTekst.setText(logik.getSynligtOrd());
//            logik.wrongLetters(logik.getBrugteBogstaver().toString(), logik.getOrdet());

            logik.logStatus();
            if (logik.isSpilletErVundet()) {
                Toast.makeText(this, "Du vandt!", Toast.LENGTH_SHORT).show();
            }
            if (logik.isSpilletErTabt()) {
                Toast.makeText(this, "Du tabte!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
