package com.bkyzsa.heroeskotprog.units;

/**
 * Az egységek fő absztrakt osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

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

    /**
     * az osztály konstruktora
     *
     * @param nev   egység neve
     * @param db    egység darabszáma
     * @param ar    egység ára
     * @param sebzesinterval1   sebzés intervallum alacsonyabb végpontja
     * @param sebzesinterval2   sebzés intervallum magasabb végpontja
     * @param hp                egység alapértelmezett életereje
     * @param sebesseg          az egység sebessége
     * @param kezdemenyezes     az egység kezdeményezése
     * @param kepesseg          az egység képessége szövegként
     * @param gazda             az egységet birtokló hős
     */

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

    /**
     * A comparable osztály compareTo() metódus felüldefiniálása.
     * @param o Egy egység kezdeményezését hasonlítjuk össze egy másikkal.
     * @return
     */
    @Override
    public int compareTo(Egyseg o) {
        return (int)(this.kezdemenyezes - o.kezdemenyezes);
    }

    /**
     * egység név gettere
     */
    public String getNev() {
        return nev;
    }
    /**
     * egység darabszám gettere
     */
    public int getDb() {
        return db;
    }
    /**
     * egység ár gettere
     */
    public int getAr() {
        return ar;
    }

    /**
     * egység sebzés alsóhatár gettere
     */
    public int getSebzesinterval1() {
        return sebzesinterval1;
    }

    /**
     * egység sebzés felsőhatár gettere
     */
    public int getSebzesinterval2() {
        return sebzesinterval2;
    }
    /**
     * egység alapérzelmezett életerő gettere
     */
    public int getHp() {
        return hp;
    }

    /**
     * egység sebesség gettere
     */
    public int getSebesseg() {
        return sebesseg;
    }

    /**
     * egység kezdeményezés gettere
     */
    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    /**
     * egység gazdahős gettere
     */
    public Hos getGazda() { return gazda; }

    public void setKezdemenyezes(int kezdemenyezes) {
        this.kezdemenyezes = kezdemenyezes;
    }

    public void setDb(int db) {
        this.db = db;
    }

    /**
     * egység összesített életerő gettere
     */
    public int getOsszHp() {
        return osszHp;
    }

    /**
     * egység összesített életerő settere
     */
    public void setOsszHp(int osszhp) {
        this.osszHp = osszhp;
    }

    /**
     * egység távolharci tulajdonságának gettere
     */

    public boolean isTavolHarci() {
        return tavolHarci;
    }

    /**
     * egység távolharci tulajdonság settere
     */

    public void setTavolHarci(boolean tavolHarci) {
        this.tavolHarci = tavolHarci;
    }

    /**
     * egység visszatámadás eshetőségének gettere
     */
    public boolean isVisszatamadtAKorben() {
        return visszatamadtAKorben;
    }

    /**
     * egység visszatámadás eshetőségének settere
     */

    public void setVisszatamadtAKorben(boolean visszatamadtAKorben) {
        this.visszatamadtAKorben = visszatamadtAKorben;
    }

    /**
     * egység kimaradásának gettere
     */
    public boolean isKimarad() {
        return kimarad;
    }

    /**
     * egység kimaradása a körből settere
     */
    public void setKimarad(boolean kimarad) {
        this.kimarad = kimarad;
    }
    /**
     * egység sebzés alsóhatár settere
     */
    public void setSebzesinterval1(int sebzesinterval1) {
        this.sebzesinterval1 = sebzesinterval1;
    }

    /**
     * egység sebzés felsőhatár settere
     */
    public void setSebzesinterval2(int sebzesinterval2) {
        this.sebzesinterval2 = sebzesinterval2;
    }

    /**
     * egység képesség absztrakt metódusa
     */
    public abstract void kepesseg();

    /**
     * egység felüldefiniált toString metódusa
     * @return szép string az adattagokból
     */
    @Override
    public String toString() {
        return "Életerő: " + hp + "\nSebzés: " + sebzesinterval1 + "-" + sebzesinterval2 + "\nÁr: " + ar + " arany / egység\nSebesség: " + sebesseg + "\nKezdeményezés: " + kezdemenyezes + "\nSpeciális képessége: " + kepesseg;
    }

    //public abstract void tamad();
    //public abstract void kepesseg();




}
