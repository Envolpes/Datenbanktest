package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String json_string;

    //Deklaration Variablen Sprung
    EditText Satz1, Satz2, Satz3, Springer;
    Button Springen;
    RequestQueue requestQueue;
    //URL Location
    String insertURL = "http://91.67.242.37/json_insert.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Refernzierung der Felder

        Satz1 = (EditText) findViewById(R.id.Satz1);
        Satz2 = (EditText) findViewById(R.id.Satz2);
        Satz3 = (EditText) findViewById(R.id.Satz3);
        Springer= (EditText) findViewById(R.id.Springer);
        Springen = (Button) findViewById(R.id.springen);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Springen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("Springer", Springer.getText().toString());
                        parameters.put("Satz1", Satz1.getText().toString());
                        parameters.put("Satz2", Satz2.getText().toString());
                        parameters.put("Satz3", Satz3.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });

    }


    public void getJSON(View view) {

    new BackgroundTask().execute();



    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{

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
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(result);
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
