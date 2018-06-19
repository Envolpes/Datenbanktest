package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Menue extends AppCompatActivity {
//Das hier ist die Menuü-Activity von der alle anderen Activities erben um ein einheitlichen Menü imm anzuzeigen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menue,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handling der drei Auswahlmöglichkeiten des Menüs
        switch (item.getItemId()){
            case R.id.Logout:
                Intent goToMainActivity = new Intent(getApplicationContext(), Login.class);
                startActivity(goToMainActivity);
                Toast.makeText(getApplicationContext(),"Erfolgreich Ausgeloggt",Toast.LENGTH_SHORT).show();
                break;
            case R.id.AboutUs:
                Toast.makeText(getApplicationContext(),"AboutUs",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Help:
                Toast.makeText(getApplicationContext(),"Dir sei geholfen",Toast.LENGTH_SHORT).show();
                break;
            default:

        }
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void ToolbarEinfügen (){
        //Toolbar einfügen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);
        //Zurück-Button einfügen
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
