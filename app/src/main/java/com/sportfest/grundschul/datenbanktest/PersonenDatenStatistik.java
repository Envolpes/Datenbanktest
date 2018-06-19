package com.sportfest.grundschul.datenbanktest;

public class PersonenDatenStatistik {

    private String weite, klasse, unterklasse, name;

    //Constructor für setDaten
    public PersonenDatenStatistik(String weite, String klasse, String unterklasse, String name){

        this.setWeite(weite);
        this.setKlasse(klasse);
        this.setUnterklasse(unterklasse);
        this.setName(name);

    }

    //GETter und SETter_Methoden
    public String getWeite() {
        return weite;
    }

    public void setWeite(String weite) {
        this.weite = weite;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getUnterklasse() {
        return unterklasse;
    }

    public void setUnterklasse(String unterklasse) {
        this.unterklasse = unterklasse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
