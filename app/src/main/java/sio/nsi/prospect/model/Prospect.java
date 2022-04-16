package sio.nsi.prospect.model;

public class Prospect {
    private int Id;
    private String nom;
    private String prenom;
    private String siret;
    private String raisonSociale;
    private int score;

    public Prospect(String nom, String prenom, String siret, String raisonSociale, int score) {
        this.nom = nom;
        this.prenom = prenom;
        this.siret = siret;
        this.raisonSociale = raisonSociale;
        this.score = score;
    }

    public Prospect(int id, String nom, String prenom, String siret, String raisonSociale, int score) {
        Id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.siret = siret;
        this.raisonSociale = raisonSociale;
        this.score = score;
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

