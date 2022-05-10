package sio.nsi.prospect.model;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Prospect {
    private int Id;
    private String nom;
    private String prenom;
    private String siret;
    private String raisonSociale;
    private int score;
    private String mail;
    private String tel;

    //Constructor

    public Prospect(int id, String nom, String prenom, String siret, String raisonSociale, int score, String mail, String tel) {
        this.Id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.siret = siret;
        this.raisonSociale = raisonSociale;
        this.score = score;
        this.mail = mail;
        this.tel = tel;
    }

    public Prospect(String nom, String prenom, String siret, String raisonSociale, int score, String mail, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.siret = siret;
        this.raisonSociale = raisonSociale;
        this.score = score;
        this.mail = mail;
        this.tel = tel;
    }

    //Methode

    public static
    int getJsonArraySize(String data)throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").length();
        }catch (JSONException err){
            return 0;
        }
    }

    public static
    int getProscpectId(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getInt("id");
        }catch (JSONException err){
            return 0;
        }
    }

    public static @NotNull
    String getProscpectNom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("nom");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectPrenom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("prenom");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectSiret(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("siret");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectRaisonSocial(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("raisonsocial");
        }catch (JSONException err){
            return "";
        }
    }

    public static
    int getProscpectScore(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getInt("score");
        }catch (JSONException err){
            return -1;
        }
    }

    public static @NotNull
    String getProscpectMail(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("mail");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectTel(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("tel");
        }catch (JSONException err){
            return "";
        }
    }

    //Getter And Setter

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

