package com.sportfest.grundschul.datenbanktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DatenAdapter datenAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        listView = (ListView)findViewById(R.id.listview);
        datenAdapter = new DatenAdapter(this,R.layout.row_layout);
        listView.setAdapter(datenAdapter);

        //Hier wird "json_data" von MainAcitivity zu DisplayListView übergeben
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = new JSONObject(json_string).getJSONArray("server_response");
            int count =0;
            String nummer, klasse, unterklasse, name;


            while(count < jsonArray.length()){

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


    }
}
