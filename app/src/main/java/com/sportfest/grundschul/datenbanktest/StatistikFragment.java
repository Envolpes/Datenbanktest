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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StatistikFragment extends Fragment {


    public StatistikFragment() {
        //Leerer COnstructor wird benötigt
    }

    private Button btnBester, btnBesteSpruenge, btnSchwimmen, btnSprint, btn_Speerwurf;
    String json_string;
    String Klasse = "1";
    String UnterKlasse = "A";
    private RadioGroup radiogroupSex;
    private RadioButton radioSexButton;
    TextView AuswahlCheckbox;
    Spinner dropdown, dropdownU;

    //FÜR JSON STRING
    String befehl;
    String JSON_STRING;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Zuweisung von allen Buttons / DropDowns/ etc...
        View activeLayout2 = inflater.inflate(R.layout.fragment_statistik, container, false);

        SharedPreferences SpinnerAuswahl = getActivity().getSharedPreferences("AuswahlSpinner", 0);

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
        btnSchwimmen = activeLayout2.findViewById(R.id.btnSchwimmen);
        btnSprint = activeLayout2.findViewById(R.id.btnSprint);

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
                AuswahlCheckbox.setText(radioSexButton.getText());

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
                AuswahlCheckbox.setText(radioSexButton.getText());

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
            //Intent mit Aufruf der DisplayListViewStatistik
            JSON_STRING = result;
            Intent intent = new Intent(getActivity(), DisplayListViewStatistik.class);
            intent.putExtra("json_data", JSON_STRING);
            startActivity(intent);
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
