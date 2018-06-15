package com.sportfest.grundschul.datenbanktest;

public class PersonenDaten {

    private String nummer, klasse, unterklasse, name;

    //Constructor
    public PersonenDaten(String nummer, String klasse, String unterklasse, String name){

        this.setNummer(nummer);
        this.setKlasse(klasse);
        this.setUnterklasse(unterklasse);
        this.setName(name);

    }

    //GETter- und SETter-MEthoden
    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
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
