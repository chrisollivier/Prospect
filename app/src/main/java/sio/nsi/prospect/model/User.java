package sio.nsi.prospect.model;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class User {
    private int Id;
    private String email;
    private String password;
    private String nom;
    private String prenom;

    //constructor

    public User(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String nom, String prenom) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(int Id, String email, String password, String nom, String prenom) {
        this.Id =Id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    //Methode

    public static
    int getJsonArraySize(String data)throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").length();
        }catch (JSONException err){
            return 0;
        }
    }

    public static @NotNull
    String getAppUserMail(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("email");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getAppUserPassword(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("password");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getAppUserNom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("nom");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getAppUserPrenom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("prenom");
        }catch (JSONException err){
            return "";
        }
    }

    //Getter And Setter

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}