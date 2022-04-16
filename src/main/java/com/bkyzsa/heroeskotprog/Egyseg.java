package com.bkyzsa.heroeskotprog;

public abstract class Egyseg implements Comparable<Egyseg>{

    private final String nev;
    private int db;
    private final int ar;
    private final int sebzesinterval1;
    private final int sebzesinterval2;
    private final int hp;
    private final int sebesseg;
    private int kezdemenyezes;
    private final String kepesseg;
    private int osszHp;
    private Hos gazda;

    public Egyseg(String nev, int db, int ar, int sebzesinterval1, int sebzesinterval2, int hp, int sebesseg, int kezdemenyezes, String kepesseg, Hos gazda) {
        this.nev = nev;
        this.db = db;
        this.ar = ar;
        this.sebzesinterval1 = sebzesinterval1;
        this.sebzesinterval2 = sebzesinterval2;
        this.hp = hp;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.kepesseg = kepesseg;
        this.gazda = gazda;
    }

    @Override
    public int compareTo(Egyseg o) {
        return (int)(this.kezdemenyezes - o.kezdemenyezes);
    }

    public String getNev() {
        return nev;
    }

    public int getDb() {
        return db;
    }

    public int getAr() {
        return ar;
    }

    public int getSebzesinterval1() {
        return sebzesinterval1;
    }

    public int getSebzesinterval2() {
        return sebzesinterval2;
    }

    public int getHp() {
        return hp;
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    public Hos getGazda() { return gazda; }

    public void setKezdemenyezes(int kezdemenyezes) {
        this.kezdemenyezes = kezdemenyezes;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public int getOsszHp() {
        return osszHp;
    }

    public void setOsszHp(int osszhp) {
        this.osszHp = osszhp;
    }



    @Override
    public String toString() {
        return "Életerő: " + hp + "\nSebzés: " + sebzesinterval1 + "-" + sebzesinterval2 + "\nÁr: " + ar + " arany / egység\nSebesség: " + sebesseg + "\nKezdeményezés: " + kezdemenyezes + "\nSpeciális képessége: " + kepesseg;
    }

    //public abstract void tamad();
    //public abstract void kepesseg();




}
