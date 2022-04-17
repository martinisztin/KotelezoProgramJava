package com.bkyzsa.heroeskotprog;

public abstract class Egyseg implements Comparable<Egyseg>{

    private final String nev;
    private int db;
    private final int ar;
    private int sebzesinterval1;
    private int sebzesinterval2;
    private final int hp;
    private final int sebesseg;
    private int kezdemenyezes;
    private final String kepesseg;
    private int osszHp;
    private Hos gazda;
    private boolean visszatamadtAKorben;
    private boolean tavolHarci;
    private boolean kimarad;

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
        this.visszatamadtAKorben = false;
        this.tavolHarci = false;
        this.kimarad = false;
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

    public boolean isTavolHarci() {
        return tavolHarci;
    }

    public void setTavolHarci(boolean tavolHarci) {
        this.tavolHarci = tavolHarci;
    }

    public boolean isVisszatamadtAKorben() {
        return visszatamadtAKorben;
    }

    public void setVisszatamadtAKorben(boolean visszatamadtAKorben) {
        this.visszatamadtAKorben = visszatamadtAKorben;
    }

    public boolean isKimarad() {
        return kimarad;
    }

    public void setKimarad(boolean kimarad) {
        this.kimarad = kimarad;
    }

    public void setSebzesinterval1(int sebzesinterval1) {
        this.sebzesinterval1 = sebzesinterval1;
    }

    public void setSebzesinterval2(int sebzesinterval2) {
        this.sebzesinterval2 = sebzesinterval2;
    }

    public abstract void kepesseg();

    @Override
    public String toString() {
        return "Életerő: " + hp + "\nSebzés: " + sebzesinterval1 + "-" + sebzesinterval2 + "\nÁr: " + ar + " arany / egység\nSebesség: " + sebesseg + "\nKezdeményezés: " + kezdemenyezes + "\nSpeciális képessége: " + kepesseg;
    }

    //public abstract void tamad();
    //public abstract void kepesseg();




}
