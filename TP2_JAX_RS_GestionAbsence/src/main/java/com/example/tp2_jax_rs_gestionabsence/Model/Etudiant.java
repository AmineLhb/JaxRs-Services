package com.example.tp2_jax_rs_gestionabsence.Model;

public class Etudiant {
    private String nom;
    private String prenom;
    private String cne;
    private int niveau;
    private double taux;

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
    public String getCne() {
        return cne;
    }
    public void setCne(String cne) {
        this.cne = cne;
    }
    public int getNiveau() {
        return niveau;
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    public double getTaux() {
        return taux;
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }

    public Etudiant(String nom, String prenom, String cne, int niveau, double taux) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.niveau = niveau;
        this.taux = taux;
    }
    public Etudiant() {
    }

}
