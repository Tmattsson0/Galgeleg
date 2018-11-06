package com.example.thomasmattsson.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLOutput;

import Data.TextReader;
import SpilLogik.GalgeLogik;

public class SpilActivity extends AppCompatActivity implements View.OnClickListener {

    GalgeLogik logik = new GalgeLogik();
//    TextReader txtrdr = new TextReader();

    Button letterButton;

    Button buttonA;

    TextView gætteTekst, forkerteBogstaver;
    EditText gætteBogstav;
    GridLayout letterGrid;

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
        letterGrid = findViewById(R.id.gridLayout);

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

//                //Gæt knappen
//                if (v == letterButton) {
//                    String BogstavGæt = (gætteBogstav.getText().toString());
//
//                    if (TextUtils.isEmpty(BogstavGæt)) {
//                        Toast.makeText(this, "Indtast venligst et bogstav", Toast.LENGTH_SHORT).show();
//                    }
//                    logik.gætBogstav(BogstavGæt);
//                    forkerteBogstaver.setText("Forkerte bogstaver [" + logik.getAntalForkerteBogstaver() + " ud af 7]: \n" + logik.wrongLetters(logik.getBrugteBogstaver().toString(), logik.getOrdet()));
//                    gætteTekst.setText(logik.getSynligtOrd());
//                    gætteBogstav.setText("");
//
//
//                    logik.logStatus();
//            if (logik.isSpilletErVundet()) {
//                Toast.makeText(this, "Du vandt!", Toast.LENGTH_SHORT).show();
//            }
//            if (logik.isSpilletErTabt()) {
//                Toast.makeText(this, "Du tabte!", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    //Button is pressed with method defined in XML
    public void onBtnClicked(View v) {
        letterButton = findViewById(v.getId());
        letterButtonPressed(letterButton);

//        for(int i=0; i<letterGrid.getChildCount(); i++) {
//            if(letterButton == (Button) letterGrid.getChildAt(i)){
//                System.out.println("Jeg fandt " + letterButton.getText().toString().toLowerCase());
//                letterButtonPressed(letterButton);
//            }


//        switch (v.getId()) {
//            case R.id.A_button:
//                Toast.makeText(this, "A!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.B_button:
//                Toast.makeText(this, "B!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.C_button:
//                Toast.makeText(this, "C!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.D_button:
//                Toast.makeText(this, "D!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.E_button:
//                Toast.makeText(this, "E!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.F_button:
//                Toast.makeText(this, "F!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.G_button:
//                Toast.makeText(this, "G!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.H_button:
//                Toast.makeText(this, "H!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.I_button:
//                Toast.makeText(this, "I!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.J_button:
//                Toast.makeText(this, "J!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.K_button:
//                Toast.makeText(this, "K!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.L_button:
//                Toast.makeText(this, "L!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.M_button:
//                Toast.makeText(this, "M!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.N_button:
//                Toast.makeText(this, "N!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.O_button:
//                Toast.makeText(this, "O!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.P_button:
//                Toast.makeText(this, "P!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.Q_button:
//                Toast.makeText(this, "Q!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.R_button:
//                Toast.makeText(this, "R!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.S_button:
//                Toast.makeText(this, "S!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.T_button:
//                Toast.makeText(this, "T!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.U_button:
//                Toast.makeText(this, "U!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.V_button:
//                Toast.makeText(this, "V!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.X_button:
//                Toast.makeText(this, "X!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.Y_button:
//                Toast.makeText(this, "Y!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.Z_button:
//                Toast.makeText(this, "Z!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.Æ_button:
//                Toast.makeText(this, "Æ!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.Ø_button:
//                Toast.makeText(this, "Ø!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//            case R.id.Å_button:
//                Toast.makeText(this, "Å!", Toast.LENGTH_SHORT).show();
//                letterButtonPressed(letterButton);
//                break;
//        }
}

    public void letterButtonPressed(Button button) {
        //Gæt knappen
        String BogstavGæt = (button.getText().toString().toLowerCase());

//        if (TextUtils.isEmpty(BogstavGæt)) {
//            Toast.makeText(this, "Indtast venligst et bogstav", Toast.LENGTH_SHORT).show();
//        }

        logik.gætBogstav(BogstavGæt);
        forkerteBogstaver.setText("Forkerte bogstaver [" + logik.getAntalForkerteBogstaver() + " ud af 7]: \n" + logik.wrongLetters(logik.getBrugteBogstaver().toString(), logik.getOrdet()));
        gætteTekst.setText(logik.getSynligtOrd());
        button.setVisibility(View.GONE);

//      gætteBogstav.setText("");

        logik.logStatus();
        if (logik.isSpilletErVundet()) {
            Toast.makeText(this, "Du vandt!", Toast.LENGTH_SHORT).show();
        }
        if (logik.isSpilletErTabt()) {
            Toast.makeText(this, "Du tabte!", Toast.LENGTH_SHORT).show();
        }
    }
}
