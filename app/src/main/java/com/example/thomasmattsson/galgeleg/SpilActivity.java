package com.example.thomasmattsson.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import Data.TextReader;
import SpilLogik.GalgeLogik;

public class SpilActivity extends AppCompatActivity implements View.OnClickListener {

    GalgeLogik logik = new GalgeLogik();
    TextReader txtrdr = new TextReader();

    Button button2;
    TextView gætteTekst, forkerteBogstaver;
    EditText gætteBogstav;

    public SpilActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        //FindView
        gætteTekst = (TextView) findViewById(R.id.textView5);
//        button2 = (Button) findViewById(R.id.button2);
//        gætteBogstav = (EditText) findViewById(R.id.editText);
        forkerteBogstaver = (TextView) findViewById(R.id.textView);

        //OnClick
//        button2.setOnClickListener(this);
        gætteTekst.setOnClickListener(this);
//        gætteBogstav.setOnClickListener(this);
        forkerteBogstaver.setOnClickListener(this);

        //Obscure word method
        gætteTekst.setText(logik.getSynligtOrd());
        System.out.println("onCreate: Ordet er: " + logik.getOrdet());
    }

    @Override
    public void onClick(View v) {

        //Gæt knappen
        if (v == button2) {
            String BogstavGæt = (gætteBogstav.getText().toString());

            if (TextUtils.isEmpty(BogstavGæt)) {
                Toast.makeText(this, "Indtast venligst et bogstav", Toast.LENGTH_SHORT).show();
            }
            logik.gætBogstav(BogstavGæt);
            forkerteBogstaver.setText("Forkerte bogstaver [" + logik.getAntalForkerteBogstaver() + " ud af 7]: \n" + logik.wrongLetters(logik.getBrugteBogstaver().toString(), logik.getOrdet()));
            gætteTekst.setText(logik.getSynligtOrd());
            gætteBogstav.setText("");


            logik.logStatus();
            if (logik.isSpilletErVundet()) {
                Toast.makeText(this, "Du vandt!", Toast.LENGTH_SHORT).show();
            }
            if (logik.isSpilletErTabt()) {
                Toast.makeText(this, "Du tabte!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onBtnClicked(View v) {
        switch (v.getId()){
            case R.id.A_button:
                Toast.makeText(this, "A!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B_button:
                Toast.makeText(this, "B!", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
