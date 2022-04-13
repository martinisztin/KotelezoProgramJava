package com.bkyzsa.heroeskotprog;

public abstract class Egyseg {

    private String nev;
    private int db;
    private int ar;
    private int sebzesinterval1;
    private int sebzesinterval2;
    private int hp;
    private int sebesseg;
    private int kezdemenyezes;
    private String kepesseg;

    public Egyseg(String nev, int db, int ar, int sebzesinterval1, int sebzesinterval2, int hp, int sebesseg, int kezdemenyezes, String kepesseg) {
        this.nev = nev;
        this.db = db;
        this.ar = ar;
        this.sebzesinterval1 = sebzesinterval1;
        this.sebzesinterval2 = sebzesinterval2;
        this.hp = hp;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.kepesseg = kepesseg;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public int getSebzesinterval1() {
        return sebzesinterval1;
    }

    public void setSebzesinterval1(int sebzesinterval1) {
        this.sebzesinterval1 = sebzesinterval1;
    }

    public int getSebzesinterval2() {
        return sebzesinterval2;
    }

    public void setSebzesinterval2(int sebzesinterval2) {
        this.sebzesinterval2 = sebzesinterval2;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public void setSebesseg(int sebesseg) {
        this.sebesseg = sebesseg;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    public void setKezdemenyezes(int kezdemenyezes) {
        this.kezdemenyezes = kezdemenyezes;
    }

    @Override
    public String toString() {
        return "Sebzés: " + sebzesinterval1 + "-" + sebzesinterval2 + "\nÁr: " + ar + " arany / egység\nSebesség: " + sebesseg + "\nKezdeményezés: " + kezdemenyezes + "\nSpeciális képessége: " + kepesseg;
    }

    //public abstract void tamad();
    //public abstract void kepesseg();




}
