package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {

    //Deklaration Variablen
    EditText txtUserName, txtPassword;
    TextView txtView1;
    Button btnLogIn;

    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referenzieren der Felder
        txtUserName = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtView1 = (TextView) findViewById(R.id.txtView1);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Login-Click, welcher die Daten an Background Task schickt
                new BackgroundTask(txtUserName.getText().toString(), txtPassword.getText().toString()).execute();
            }
        });

        //Hier wird in Cookie gespeichert
        SharedPreferences mySPR = getSharedPreferences("MySprFile", 0);
        txtUserName.setText(mySPR.getString("username", ""));
        txtPassword.setText(mySPR.getString("password", ""));
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        //Dekleration der Parameter
        String json_url;
        String JSON_STRING;
        String benutzername;
        String pw;

        public BackgroundTask(String benutzername, String pw) {

            //Constructor, um Daten zu erhalten
            this.benutzername = benutzername;
            this.pw = pw;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            //URL für Login
            json_url = "http://91.67.234.134/check_login.php?" + "name=" + benutzername + "&pw=" + pw;

            try {
                //Auflösen der URL in HTTP und Aufbau eines String-Builders
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JSON_STRING + "\n");
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
            try {
                //Auflösen des Strings in das finale JSONObject
                JSONObject ValidateLogin = new JSONObject(result).getJSONArray("server_response").getJSONObject(0);
                Intent goToHome = new Intent(getApplicationContext(), Home.class);
                startActivity(goToHome);
                Toast.makeText(getApplicationContext(), "Erfolgreich Eingeloggt", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                //Alternative, falls leer
                e.printStackTrace();
                txtView1.setText("Username oder Password falsch");

            }
        }
    }

    @Override
    protected void onStop() {
        //Quasi Cookie, welcher die Eingabedaten speichert
        super.onStop();
        SharedPreferences mySPR = getSharedPreferences("MySprFile", 0);
        SharedPreferences.Editor editor = mySPR.edit();
        editor.putString("username", txtUserName.getText().toString());
        editor.putString("password", txtPassword.getText().toString());
        editor.commit();
    }
}
