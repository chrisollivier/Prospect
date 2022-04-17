package sio.nsi.prospect.model;

public class Prospect {
    private int Id;
    private String nom;
    private String prenom;
    private String siret;
    private String raisonSociale;
    private int score;
    private String mail;
    private String tel;

    public Prospect(String nom, String prenom, String siret, String raisonSociale, int score, String mail, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.siret = siret;
        this.raisonSociale = raisonSociale;
        this.score = score;
        this.mail = mail;
        this.tel = tel;
    }

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

