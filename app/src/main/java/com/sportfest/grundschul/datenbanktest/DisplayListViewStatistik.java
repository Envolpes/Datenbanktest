package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class DisplayListViewStatistik extends Menue {

    //Deklaration der Variablen
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DatenAdapterStatistik datenAdapter;
    ListView listView;
    Array x;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.listview);
        datenAdapter = new DatenAdapterStatistik(this,R.layout.row_layout_statistik);
        listView.setAdapter(datenAdapter);

        //Hier wird "json_data" von MainAcitivity zu DisplayListView übergeben
        json_string = getIntent().getExtras().getString("json_data");

        try {

            //JSON String wird in Object und Array geschrieben
            jsonObject = new JSONObject(json_string);
            jsonArray = new JSONObject(json_string).getJSONArray("server_response");
            int count =0;
            //Ab hier ändndern auf Beste Sprünge
            String weite, klasse, unterklasse, name;


            while(count < jsonArray.length()){

                //JSON Array wird ausgelesen und in die Variablen geschrieben
                JSONObject JO = jsonArray.getJSONObject(count);

                weite = JO.getString("Beste Weite");
                name = JO.getString("Springer");
                klasse = JO.getString("Klasse");
                unterklasse = JO.getString("UnterKlasse");
                PersonenDatenStatistik personenDaten = new PersonenDatenStatistik(weite, klasse, unterklasse, name);

                datenAdapter.add(personenDaten);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

try {
    JSONObject JO = jsonArray.getJSONObject(1);
    String weite, klasse, unterklasse, name;

    weite = JO.getString("Beste Weite");
    name = JO.getString("Springer");
    klasse = JO.getString("Klasse");
    unterklasse = JO.getString("UnterKlasse");

    PersonenDatenStatistik personenDaten = new PersonenDatenStatistik(weite, klasse, unterklasse, name);
    Toast.makeText(getApplicationContext(), personenDaten.getName(), Toast.LENGTH_LONG).show();

}
catch (JSONException e) {
    e.printStackTrace();
}

    }
}
