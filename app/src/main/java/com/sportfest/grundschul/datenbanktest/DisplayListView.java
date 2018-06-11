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

public class DisplayListView extends Menue {

    //Deklaration der Variablen
    String json_string;
    Boolean nochmal;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DatenAdapter datenAdapter;
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
                PersonenDaten personenDaten = new PersonenDaten(nummer, klasse, unterklasse, name);

                datenAdapter.add(personenDaten);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Click auf Schülernamen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(), SprungEingabe.class);

                //SprungEingabe mit Schüler wird aufgerufen
               try {
                    intent.putExtra("Schueler", jsonArray.get(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });
       /* try {
            jsonArray = new JSONObject(json_string).getJSONArray("server_response");
            List<String> list = new ArrayList<String>();
            for (int i=0; i<jsonArray.length(); i++) {
                list.add( jsonArray.getString(i) );
            }
            Toast.makeText(getApplicationContext(),list.get(0), Toast.LENGTH_LONG).show();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }*/
try {
    JSONObject JO = jsonArray.getJSONObject(1);
    String nummer, klasse, unterklasse, name;

    nummer = JO.getString("nummer");
    klasse = JO.getString("klasse");
    unterklasse = JO.getString("unterklasse");
    name = JO.getString("name");

    PersonenDaten personenDaten = new PersonenDaten(nummer, klasse, unterklasse, name);
    Toast.makeText(getApplicationContext(), personenDaten.getNummer(), Toast.LENGTH_LONG).show();

}
catch (JSONException e) {
    e.printStackTrace();
}

    }
}
