package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Home extends Menue {

    private Button btnDisziplin, btnStatistik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);

        btnDisziplin = findViewById(R.id.btnDisziplin);
        btnStatistik = findViewById(R.id.btnStatistik);

        btnStatistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotToStatistik = new Intent(getApplicationContext(), Statistik.class);
                startActivity(gotToStatistik);
            }
        });
        btnDisziplin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDisziplin = new Intent(getApplicationContext(), Disziplin.class);
                startActivity(goToDisziplin);
            }
        });
    }
}
