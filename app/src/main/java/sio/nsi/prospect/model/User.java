package sio.nsi.prospect.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private int actuelScore;
    private int lastScore;


    public User(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getActuelScore() {
        return actuelScore;
    }

    public void setActuelScore(int actuelScore) {
        this.actuelScore = actuelScore;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }
}