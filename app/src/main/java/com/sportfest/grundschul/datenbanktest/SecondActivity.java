package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends Menue {
    TextView Name;
    TextView Klasse;
    TextView Nummer;
    TextView UKlasse;
    TextView JSON;

    //Deklaration Variablen Sprung
    EditText Satz1, Satz2, Satz3;
    Button Springen;
    RequestQueue requestQueue;
    //URL Location
    String insertURL = "http://91.67.242.37/json_insert.php";
    String JSON_Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        String jsonInString = bundle.getString("Schueler");
        Daten user = gson.fromJson(jsonInString, Daten.class);



        Name = (TextView) findViewById(R.id.Name);
        Klasse = (TextView) findViewById(R.id.klasse);
        Nummer = (TextView) findViewById(R.id.Nummer);
        UKlasse = (TextView) findViewById(R.id.unterklasse);
        JSON = (TextView) findViewById(R.id.textView2);

        //Daten werden vorbefüllt

        Klasse.setText(user.getKlasse());
        Name.setText(user.getName());
        Nummer.setText(user.getNummer());
        UKlasse.setText(user.getUnterklasse());
        String StNummer = user.getNummer();

        //Refernzierung der Felder

        Satz1 = (EditText) findViewById(R.id.Satz1);
        Satz2 = (EditText) findViewById(R.id.Satz2);
        Satz3 = (EditText) findViewById(R.id.Satz3);
        Springen = (Button) findViewById(R.id.springen);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getJSON(StNummer);

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


                //Sprung in ListView
                finish();

            }
        });


    }

    public void getJSON(String Nummer) {

        new SecondActivity.BackgroundTask(Nummer).execute();

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;
        String Nummer;
        String JSON_STRING;

        public BackgroundTask(String Nummer){

            this.Nummer= Nummer;

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            json_url ="http://91.67.242.37/sprungabfrage.php?Springer="+Nummer;

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
            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText(result);
            JSON_Text= result;


        }
    }

}




/*ToDo:
// -Man darf nicht 0 eingeben - NeTu
// -Falls Werte vorhanden sind, sollen diese dort erscheinen - NeTu / Mawe
//          -JSON String, welcher vorhandene Daten schickt - MaWe
            -Die Daten sollen in den Feldern eingefügt werden - NeTu/(MaWe)

*/