package com.sportfest.grundschul.datenbanktest;

public class SprungDaten {
    private String nummer, sprung1, sprung2, sprung3;

    public SprungDaten (String nummer, String sprung1, String sprung2, String sprung3){

        this.setNummer(nummer);
        this.setSprung1(sprung1);
        this.setSprung2(sprung2);
        this.setSprung3(sprung3);

    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getSprung1() {
        return sprung1;
    }

    public void setSprung1(String sprung1) {
        this.sprung1 = sprung1;
    }

    public String getSprung2() {
        return sprung2;
    }

    public void setSprung2(String sprung2) {
        this.sprung2 = sprung2;
    }

    public String getSprung3() {
        return sprung3;
    }

    public void setSprung3(String sprung3) { this.sprung3 = sprung3; }

}
