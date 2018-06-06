package com.sportfest.grundschul.datenbanktest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends Menue {

    //Deklaration der Variablen
    String json_string;
    Boolean nochmal;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DatenAdapter datenAdapter;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.listview);
        datenAdapter = new DatenAdapter(this,R.layout.row_layout);
        listView.setAdapter(datenAdapter);

        //Hier wird "json_data" von MainAcitivity zu DisplayListView übergeben
        json_string = getIntent().getExtras().getString("json_data");

        try {

            //JSON String wird in Object und Array geschrieben
            jsonObject = new JSONObject(json_string);
            jsonArray = new JSONObject(json_string).getJSONArray("server_response");
            int count =0;
            String nummer, klasse, unterklasse, name;


            while(count < jsonArray.length()){

                //JSON Array wird ausgelesen und in die Variablen geschrieben
                JSONObject JO = jsonArray.getJSONObject(count);

                nummer = JO.getString("nummer");
                klasse = JO.getString("klasse");
                unterklasse = JO.getString("unterklasse");
                name = JO.getString("name");
                Daten daten = new Daten(nummer, klasse, unterklasse, name);

                datenAdapter.add(daten);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Click auf Schülernamen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(), SecondActivity.class);

                //SecondActivity mit Schüler wird aufgerufen
               try {
                    intent.putExtra("Schueler", jsonArray.get(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

    }
}
