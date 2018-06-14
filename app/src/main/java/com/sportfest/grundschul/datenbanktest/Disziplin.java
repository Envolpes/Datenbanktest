package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Disziplin extends Menue {
    private Button btnWeitsprung, btnSchwimmen, btnSprint, btnBestätigen;
    String json_string;
    String Klasse = "1";
    String UnterKlasse = "A";
    Spinner dropdown, dropdownU;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disziplin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences Spinnerauswahl = getSharedPreferences("Auswahlspinner",0);

        dropdown = (Spinner) findViewById(R.id.btnDropdownKlassen);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Disziplin.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownKlassen));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(myAdapter);
        dropdown.setSelection(Spinnerauswahl.getInt("AusgewählterIndexDropdown",0));

        dropdownU = (Spinner) findViewById(R.id.btnDropdownUnterklassen);
        ArrayAdapter<String> myAdapterU = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownUnterklassen));
        myAdapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownU.setAdapter(myAdapterU);
        dropdownU.setSelection(Spinnerauswahl.getInt("AusgewählterIndexDropdownU",0));

        btnWeitsprung = findViewById(R.id.btnWeitsprung_Bester);
        btnSchwimmen = findViewById(R.id.btnSchwimmen);
        btnSprint = findViewById(R.id.btnSprint);

        btnWeitsprung.setOnClickListener(new View.OnClickListener() {
        //    @Override
            public void onClick(View v) {
            //    Intent gotToWeitsprung = new Intent(getApplicationContext(), DisplayListView.class);
              //  startActivity(gotToWeitsprung);

                Klasse = dropdown.getSelectedItem().toString();
                UnterKlasse = dropdownU.getSelectedItem().toString();
                //BackgroundTask asyncTask = new BackgroundTask(Klasse, UnterKlasse);

                getJSON(Klasse,UnterKlasse);

            }
        });

        btnSchwimmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Schwimmen ausgewählt", Toast.LENGTH_SHORT).show();
            }
        });

        btnSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sprint ausgewählt", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getJSON(String Klasse, String UnterKlasse) {

        new Disziplin.BackgroundTask(Klasse,UnterKlasse).execute();

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;
        String JSON_STRING;

        private String Klasse = "1";
        private String UnterKlasse = "A";


        public BackgroundTask(String Klasse, String UnterKlasse){

            this.Klasse = Klasse;
            this.UnterKlasse= UnterKlasse;

        }
        @Override
        protected void onPreExecute() {
                 }

        @Override
        protected String doInBackground(Void... voids) {
            //Hier wird die JSON-URL aufgebaut
            json_url="http://91.67.242.37/json_get_data_schulklasse.php?klasse=" +Klasse+"&unterklasse="+UnterKlasse;
            try {

                //URL Link wir genutzt, um HTTP Connection herzustellen
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                //STRING wird aus den Lines des bufferedReader gelesen und in JSON_String geschrieben
                while((JSON_STRING = bufferedReader.readLine())!= null){

                    stringBuilder.append(JSON_STRING+"\n");
                }

                //Schließen der Connections
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

                //Notwendige Try/Catch Bedingungen
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String result) {

            //JSON Stirng wird mit übergebener Variabel befüllt
            //und daraufhin DisplayListView-Klasse mit dem Extra "json_string" aufgerufen
            json_string= result;
            Intent intent = new Intent ( getApplicationContext(), DisplayListView.class);
            intent.putExtra("json_data", json_string);
            startActivity(intent);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences Spinnerauswahl = getSharedPreferences("Auswahlspinner",0);
        SharedPreferences.Editor editor = Spinnerauswahl.edit();
        editor.putInt("AusgewählterIndexDropdown",dropdown.getSelectedItemPosition());
        editor.putInt("AusgewählterIndexDropdownU",dropdownU.getSelectedItemPosition());
        editor.commit();
    }

}
