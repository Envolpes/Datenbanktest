package com.sportfest.grundschul.datenbanktest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class Home extends Menue {

    //private Button btnDisziplin, btnStatistik;
    private BottomNavigationView main_nav;
    private FrameLayout main_frame;

    private DisziplinFragment dizFragm;
    private StatistikFragment statFragm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasis);
        setSupportActionBar(toolbar);

        main_frame = (FrameLayout) findViewById(R.id.main_frame);
        main_nav = (BottomNavigationView) findViewById(R.id.main_nav);

        dizFragm = new DisziplinFragment();
        statFragm = new StatistikFragment();

        setFragment(dizFragm);

        main_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_Diziplin:
                        setFragment(dizFragm);
                        return true;

                    case R.id.nav_Statistik:
                        setFragment(statFragm);
                        return true;


                    default:
                        return false;
                }
            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
