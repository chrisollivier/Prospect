package com.SIO.Questionner.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int id;
    private String nom;
    private String prenom;
    private int actuelScore;
    private int lastScore;

    public User(String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.lastScore = lastScore;
        this.actuelScore = actuelScore;
    }

    public User(int id,String nom, String prenom ,int bestScore) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.lastScore = bestScore;
        this.actuelScore = actuelScore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nom);
        dest.writeString(this.prenom);
        dest.writeInt(this.actuelScore);
        dest.writeInt(this.lastScore);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nom = source.readString();
        this.prenom = source.readString();
        this.actuelScore = source.readInt();
        this.lastScore = source.readInt();
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.nom = in.readString();
        this.prenom = in.readString();
        this.actuelScore = in.readInt();
        this.lastScore = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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