package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Disziplin extends Menue {
    private Button btnWeitsprung, btnSchwimmen, btnSprint;
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disziplin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner dropdown = (Spinner) findViewById(R.id.btnDropdownKlassen);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Disziplin.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownKlassen));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(myAdapter);

        Spinner dropdownU = (Spinner) findViewById(R.id.btnDropdownUnterklassen);
        ArrayAdapter<String> myAdapterU = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownUnterklassen));
        myAdapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownU.setAdapter(myAdapterU);

        btnWeitsprung = findViewById(R.id.btnWeitsprung);
        btnSchwimmen = findViewById(R.id.btnSchwimmen);
        btnSprint = findViewById(R.id.btnSprint);

        //btnWeitsprung.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
            //    Intent gotToWeitsprung = new Intent(getApplicationContext(), DisplayListView.class);
              //  startActivity(gotToWeitsprung);
           // }
       // });

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
        getJSON(null);
    }

    public void getJSON(View view) {

        new Disziplin.BackgroundTask().execute();

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;
        String JSON_STRING;



        @Override
        protected void onPreExecute() {
            //Hier wird der JSON aufgerufen
            json_url="http://91.67.242.37/json_get_data.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
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
            //TextView textView = (TextView) findViewById(R.id.textView);
            //textView.setText(result);
            json_string= result;

        }
    }


    public void parseJSON(View view) {

        if(json_string==null){
            Toast.makeText(getApplicationContext(), "First get JSON", Toast.LENGTH_LONG).show();

        }
        else{

            Intent intent = new Intent (this, DisplayListView.class);
            intent.putExtra("json_data", json_string);
            startActivity(intent);
        }

    }
}
