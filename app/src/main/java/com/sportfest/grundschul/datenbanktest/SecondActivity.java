package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    TextView Name;
    TextView Klasse;
    TextView Nummer;
    TextView UKlasse;

    //Deklaration Variablen Sprung
    EditText Satz1, Satz2, Satz3;
    Button Springen;
    RequestQueue requestQueue;
    //URL Location
    String insertURL = "http://91.67.242.37/json_insert.php";

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


        //Refernzierung der Felder

        Satz1 = (EditText) findViewById(R.id.Satz1);
        Satz2 = (EditText) findViewById(R.id.Satz2);
        Satz3 = (EditText) findViewById(R.id.Satz3);
        Springen = (Button) findViewById(R.id.springen);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Springen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //POST Methode, um Weiten zur DB zu schicken

                StringRequest request = new StringRequest(Request.Method.POST, insertURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

                    @Override

                    //Werte, die geschickt werden
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("Springer", Nummer.getText().toString());
                        parameters.put("Satz1", Satz1.getText().toString());
                        parameters.put("Satz2", Satz2.getText().toString());
                        parameters.put("Satz3", Satz3.getText().toString());

                        return parameters;
                    }
                };

                // Einfügen des Einfügen in die Warteschlange
                requestQueue.add(request);


                //Sprung in MainAcitivity

                startActivity(new Intent(SecondActivity.this, MainActivity.class));

            }
        });


    }
}
