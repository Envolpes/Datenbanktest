package com.sportfest.grundschul.datenbanktest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DatenAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public DatenAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(PersonenDaten object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row = convertView;
        DatenHolder datenHolder;

        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);

            datenHolder = new DatenHolder();
            datenHolder.tx_nummer = (TextView) row.findViewById(R.id.tx_nummer);
            datenHolder.tx_klasse = (TextView) row.findViewById(R.id.tx_klasse);
            datenHolder.tx_unterklasse = (TextView) row.findViewById(R.id.tx_unterklasse);
            datenHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            row.setTag(datenHolder);



        }

        else{

            datenHolder = (DatenHolder)row.getTag();
        }

        PersonenDaten personenDaten = (PersonenDaten) this.getItem(position);
        datenHolder.tx_nummer.setText(personenDaten.getNummer());
        datenHolder.tx_klasse.setText(personenDaten.getKlasse());
        datenHolder.tx_unterklasse.setText(personenDaten.getUnterklasse());
        datenHolder.tx_name.setText(personenDaten.getName());
        return row;
    }

    static class DatenHolder{

        TextView tx_nummer,tx_klasse, tx_unterklasse, tx_name;

    }

}
