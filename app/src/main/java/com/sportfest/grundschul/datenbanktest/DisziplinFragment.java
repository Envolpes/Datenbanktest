package com.sportfest.grundschul.datenbanktest;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisziplinFragment extends Fragment {


    public DisziplinFragment() {
        // Required empty public constructor
    }

    private Button btn_Weitsprung, btn_Schwimmen, btn_Sprint, btn_Speerwurf;
    Spinner dropdownKlassen, dropdownUnterklassen;
    String Klasse = "1";
    String UnterKlasse = "A";
    String json_string;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View activeLayout = inflater.inflate(R.layout.fragment_disziplin, container, false);

        SharedPreferences Spinnerauswahl = getActivity().getSharedPreferences("Auswahlspinner",0);


        dropdownKlassen = (Spinner) activeLayout.findViewById(R.id.btnDropdownKlassen);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownKlassen));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownKlassen.setAdapter(myAdapter);
        dropdownKlassen.setSelection(Spinnerauswahl.getInt("AusgewählterIndexDropdown",0));

        dropdownUnterklassen = (Spinner) activeLayout.findViewById(R.id.btnDropdownUnterklassen);
        ArrayAdapter<String> myAdapterU = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownUnterklassen));
        myAdapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownUnterklassen.setAdapter(myAdapterU);
        dropdownUnterklassen.setSelection(Spinnerauswahl.getInt("AusgewählterIndexDropdownU",0));

        btn_Weitsprung = activeLayout.findViewById(R.id.btn_Weitsprung);
        btn_Schwimmen = activeLayout.findViewById(R.id.btn_Schwimmen);
        btn_Sprint = activeLayout.findViewById(R.id.btn_Sprint);
        btn_Speerwurf = activeLayout.findViewById(R.id.btn_Speerwurf);

        btn_Weitsprung.setOnClickListener(new View.OnClickListener() {
            //    @Override
            public void onClick(View v) {
                //    Intent gotToWeitsprung = new Intent(getApplicationContext(), DisplayListView.class);
                //  startActivity(gotToWeitsprung);

                Klasse = dropdownKlassen.getSelectedItem().toString();
                UnterKlasse = dropdownUnterklassen.getSelectedItem().toString();
                //BackgroundTask asyncTask = new BackgroundTask(Klasse, UnterKlasse);

                getJSON(Klasse,UnterKlasse);

            }
        });

        btn_Schwimmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Schwimmen ausgewählt", Toast.LENGTH_SHORT).show();
            }
        });

        btn_Sprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Sprint ausgewählt", Toast.LENGTH_SHORT).show();
            }
        });

        btn_Speerwurf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Speerwurf ausgewählt", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return activeLayout;

    }

    public void getJSON(String Klasse, String UnterKlasse) {

        new DisziplinFragment.BackgroundTask(Klasse,UnterKlasse).execute();

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;
        String JSON_STRING;

        private String Klasse = "1";
        private String UnterKlasse = "A";


        public BackgroundTask(String Klasse, String UnterKlasse){

            this.Klasse = Klasse;
            this.UnterKlasse= UnterKlasse;

        }
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            //Hier wird die JSON-URL aufgebaut
            json_url="http://91.67.242.37/json_get_data_schulklasse.php?klasse=" +Klasse+"&unterklasse="+UnterKlasse;
            try {

                //URL Link wir genutzt, um HTTP Connection herzustellen
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                //STRING wird aus den Lines des bufferedReader gelesen und in JSON_String geschrieben
                while((JSON_STRING = bufferedReader.readLine())!= null){

                    stringBuilder.append(JSON_STRING+"\n");
                }

                //Schließen der Connections
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

                //Notwendige Try/Catch Bedingungen
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

            //JSON Stirng wird mit übergebener Variabel befüllt
            //und daraufhin DisplayListView-Klasse mit dem Extra "json_string" aufgerufen
            json_string= result;
            Intent intent = new Intent ( getContext(), DisplayListView.class);
            intent.putExtra("json_data", json_string);
            startActivity(intent);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences Spinnerauswahl = getActivity().getSharedPreferences("Auswahlspinner",0);
        SharedPreferences.Editor editor = Spinnerauswahl.edit();
        editor.putInt("AusgewählterIndexDropdown", dropdownKlassen.getSelectedItemPosition());
        editor.putInt("AusgewählterIndexDropdownU", dropdownUnterklassen.getSelectedItemPosition());
        editor.commit();
    }

}
