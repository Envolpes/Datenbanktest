package com.sportfest.grundschul.datenbanktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class SecondActivity extends AppCompatActivity {
    TextView Name;
    TextView Klasse;
    TextView Nummer;
    TextView UKlasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        String jsonInString = bundle.getString("Coutryname");
        Daten user = gson.fromJson(jsonInString, Daten.class);



        Name = (TextView) findViewById(R.id.Name);
        Klasse = (TextView) findViewById(R.id.klasse);
        Nummer = (TextView) findViewById(R.id.Nummer);
        UKlasse = (TextView) findViewById(R.id.unterklasse);




        Klasse.setText(user.getKlasse());
        Name.setText(user.getName());
        Nummer.setText(user.getNummer());
        UKlasse.setText(user.getUnterklasse());


    }
}
