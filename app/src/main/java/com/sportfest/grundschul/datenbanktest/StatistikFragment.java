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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StatistikFragment extends Fragment {

    //Deklaration der Variablen
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DatenAdapterStatistik datenAdapter;
    ListView listView;
    Button btnBester, btnBesteSpruenge;
    RadioGroup radiogroupSex;
    RadioButton radioSexButton;
    TextView AuswahlCheckbox, txt_Überschrift;
    Spinner dropdown, dropdownU;

    public StatistikFragment() {
        //Leerer COnstructor wird benötigt
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Zuweisung von allen Buttons / DropDowns/ etc...
        View activeLayout2 = inflater.inflate(R.layout.fragment_statistik, container, false);

        //Abholen der zuletzt ausgewählten Dropdownauswahlen
        SharedPreferences SpinnerAuswahl = getActivity().getSharedPreferences("AuswahlSpinner", 0);

        txt_Überschrift = activeLayout2.findViewById(R.id.txt_uberschrift);

        dropdown = (Spinner) activeLayout2.findViewById(R.id.btnDropdownKlassen);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownKlassenStatistik));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(myAdapter);
        dropdown.setSelection(SpinnerAuswahl.getInt("AusgewählterIndexDropdown", 0));

        dropdownU = (Spinner) activeLayout2.findViewById(R.id.btnDropdownUnterklassen);
        ArrayAdapter<String> myAdapterU = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownUnterklassenStatistik));
        myAdapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownU.setAdapter(myAdapterU);
        dropdownU.setSelection(SpinnerAuswahl.getInt("AusgewählterIndexDropdownU", 0));

        btnBester = activeLayout2.findViewById(R.id.btnWeitsprung_Bester);
        btnBesteSpruenge = activeLayout2.findViewById(R.id.btnWeitsprung_Beste);


        //OnClicktlistener der Buttons
        btnBesteSpruenge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Zuweisung der RadioGroup und TextView
                radiogroupSex = (RadioGroup) getActivity().findViewById(R.id.radiogroupSex);
                AuswahlCheckbox = (TextView) getActivity().findViewById(R.id.auswah);
                // get selected radio button from radioGroup
                int selectedId = radiogroupSex.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioSexButton = (RadioButton) getActivity().findViewById(selectedId);
                txt_Überschrift.setText("Beste Sprünge");


                //Aufruf der Background Task
                new BackgroundTask("beste_sprunge.php", dropdown.getSelectedItem().toString(), dropdownU.getSelectedItem().toString(), radioSexButton.getText().toString()).execute();
            }
        });

        btnBester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Zuweisung der RadioGroup und TextView
                radiogroupSex = (RadioGroup) getActivity().findViewById(R.id.radiogroupSex);
                AuswahlCheckbox = (TextView) getActivity().findViewById(R.id.auswah);
                // get selected radio button from radioGroup
                int selectedId = radiogroupSex.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioSexButton = (RadioButton) getActivity().findViewById(selectedId);
                txt_Überschrift.setText("Bester Sprung");
                //Aufruf der Background Task
                new BackgroundTask("bester.php", dropdown.getSelectedItem().toString(), dropdownU.getSelectedItem().toString(), radioSexButton.getText().toString()).execute();
            }
        });

        //Das Layout wird für dieses Fragment Inflated
        return activeLayout2;
    }

    //Gleiche wie immer
    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;
        String befehl;
        String JSON_STRING;
        String klasse;
        String unterklasse;
        String geschlecht;


        public BackgroundTask(String befehl, String klasse, String unterklasse, String geschlecht) {

            this.befehl = befehl;
            this.klasse = klasse;
            this.unterklasse = unterklasse;
            this.geschlecht = geschlecht;

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            json_url = "http://91.67.242.37/" + befehl + "?klasse=" + klasse + "&unterklasse=" + unterklasse + "&geschlecht=" + geschlecht;

            try {
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
            //Verknüpfen mit row_layout_statistik
            listView = (ListView) getActivity().findViewById(R.id.listview);
            datenAdapter = new DatenAdapterStatistik(getContext(), R.layout.row_layout_statistik);
            listView.setAdapter(datenAdapter);

            //Hier wird "json_data" übergeben
            json_string = result;

            try {

                //JSON String wird in Object und Array geschrieben
                jsonObject = new JSONObject(json_string);
                jsonArray = new JSONObject(json_string).getJSONArray("server_response");
                int count = 0;
                String weite, klasse, unterklasse, name;


                while (count < jsonArray.length()) {

                    //JSON Array wird ausgelesen und in die Variablen geschrieben
                    JSONObject JO = jsonArray.getJSONObject(count);

                    weite = JO.getString("Beste Weite");
                    name = JO.getString("Springer");
                    klasse = JO.getString("Klasse");
                    unterklasse = JO.getString("UnterKlasse");

                    //Daten werden in PersonenDatenStatistik gespeichert
                    PersonenDatenStatistik personenDaten = new PersonenDatenStatistik(weite, klasse, unterklasse, name);

                    //DatenAdapterStatistik bekommt die Informationen, welche wir eben in PersonenDatenStatistik gespeichert haben
                    datenAdapter.add(personenDaten);

                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                //Alternative, falls was nicht klappt
                JSONObject JO = jsonArray.getJSONObject(1);
                String weite, klasse, unterklasse, name;

                weite = JO.getString("Beste Weite");
                name = JO.getString("Springer");
                klasse = JO.getString("Klasse");
                unterklasse = JO.getString("UnterKlasse");

                PersonenDatenStatistik personenDaten = new PersonenDatenStatistik(weite, klasse, unterklasse, name);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onStop() {

        //Wie ein Cookie, speichert die Auswahlen der Dropdowns (Vorspeicherung der DropDowns)
        super.onStop();
        SharedPreferences SpinnerAuswahl = getActivity().getSharedPreferences("AuswahlSpinner", 0);
        SharedPreferences.Editor editor = SpinnerAuswahl.edit();
        editor.putInt("AusgewählterIndexDropdown", dropdown.getSelectedItemPosition());
        editor.putInt("AusgewählterIndexDropdownU", dropdownU.getSelectedItemPosition());
        editor.commit();
    }

}
