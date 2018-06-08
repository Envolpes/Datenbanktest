package com.sportfest.grundschul.datenbanktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Statistik1 extends Menue {
    private Button btnWeitsprung, btnSchwimmen, btnSprint, btnBestätigen;
    String json_string;
    String Klasse = "1";
    String UnterKlasse = "A";
    private RadioGroup radiogroupSex;
    private RadioButton radioSexButton;
    TextView AuswahlCheckbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik1);
        addListenerOnButton();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner dropdown = (Spinner) findViewById(R.id.btnDropdownKlassen);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownKlassen));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(myAdapter);

        final Spinner dropdownU = (Spinner) findViewById(R.id.btnDropdownUnterklassen);
        ArrayAdapter<String> myAdapterU = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropdownUnterklassenStatistik));
        myAdapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownU.setAdapter(myAdapterU);

        btnWeitsprung = findViewById(R.id.btnWeitsprung);
        btnSchwimmen = findViewById(R.id.btnSchwimmen);
        btnSprint = findViewById(R.id.btnSprint);
        btnBestätigen = findViewById(R.id.GetKlasse);

        btnBestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //Male Female Button Control
    public void addListenerOnButton() {
        radiogroupSex = (RadioGroup) findViewById(R.id.radiogroupSex);
        AuswahlCheckbox = (TextView) findViewById(R.id.auswah);
        btnWeitsprung = findViewById(R.id.btnWeitsprung);
        btnWeitsprung.setOnClickListener(new View.OnClickListener() {
            //    @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radiogroupSex.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(Statistik1.this, radioSexButton.getText(), Toast.LENGTH_SHORT).show();
                AuswahlCheckbox.setText(radioSexButton.getText());
            }
        });
    }
}
