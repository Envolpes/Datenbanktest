package com.sportfest.grundschul.datenbanktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Statistik1 extends Menue {
    private Button btnBester,btnBesteSpruenge, btnSchwimmen, btnSprint, btnBestätigen;
    String json_string;
    String Klasse = "1";
    String UnterKlasse = "A";
    private RadioGroup radiogroupSex;
    private RadioButton radioSexButton;
    TextView AuswahlCheckbox;

    //FÜR JSON STRING
    String befehl;
    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik1);
        addListenerOnButton();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner dropdown = (Spinner) findViewById(R.id.btnDropdownKlassen);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownKlassenStatistik));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(myAdapter);

        final Spinner dropdownU = (Spinner) findViewById(R.id.btnDropdownUnterklassen);
        ArrayAdapter<String> myAdapterU = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownUnterklassenStatistik));
        myAdapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownU.setAdapter(myAdapterU);

        btnBester = findViewById(R.id.btnWeitsprung_Bester);
        btnBesteSpruenge = findViewById(R.id.btnWeitsprung_Beste);
        btnSchwimmen = findViewById(R.id.btnSchwimmen);
        btnSprint = findViewById(R.id.btnSprint);
        btnBestätigen = findViewById(R.id.GetKlasse);

        btnBesteSpruenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Statistik1.BackgroundTask("beste_sprunge.php",dropdown.getSelectedItem().toString(),dropdownU.getSelectedItem().toString(), radioSexButton.getText().toString()).execute();
            }
        });

        btnBester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Statistik1.BackgroundTask("bester.php",dropdown.getSelectedItem().toString(),dropdownU.getSelectedItem().toString(), radioSexButton.getText().toString() ).execute();
            }
        });


        //Kann man löschen
        btnBestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //Male Female Button Control
    public void addListenerOnButton() {
        radiogroupSex = (RadioGroup) findViewById(R.id.radiogroupSex);
        AuswahlCheckbox = (TextView) findViewById(R.id.auswah);
        btnBester = findViewById(R.id.btnWeitsprung_Bester);
        btnBester.setOnClickListener(new View.OnClickListener() {
            //    @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radiogroupSex.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(Statistik1.this, radioSexButton.getText(), Toast.LENGTH_SHORT).show();
                //Hier um den Text für Radiobutton zu bekommen
                AuswahlCheckbox.setText(radioSexButton.getText());
            }
        });
    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;
        String befehl;
        String JSON_STRING;
        String klasse;
        String unterklasse;
        String geschlecht;


        public BackgroundTask(String befehl, String klasse, String unterklasse, String geschlecht){

            this.befehl= befehl;
            this.klasse = klasse;
            this.unterklasse= unterklasse;
            this.geschlecht = geschlecht;

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            json_url ="http://91.67.242.37/" + befehl+"?klasse="+klasse+"&unterklasse="+unterklasse + "&geschlecht=" + geschlecht;

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!= null){

                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


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
            //Hier hast den String Nehemia
            JSON_STRING=result;
        }
    }




}
