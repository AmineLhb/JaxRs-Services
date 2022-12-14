package com.example.tp2_jax_rs.Model;

public class Etudiant {
    private String nom;
    private String prenom;
    private String cne;
    private double note1;
    private double note2;
    public double getMoyen() {
        return (note1+note2)/2;
    }
    public boolean isValid() {
        double moyen = getMoyen();
        if(moyen>12) {
            return true;
        }else {
            return false;
        }
    }
    public Etudiant() {
    }
    public Etudiant(String nom, String prenom,String cne, double note1, double note2) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.note1 = note1;
        this.note2 = note2;
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
    public double getNote1() {
        return note1;
    }
    public void setNote1(double note1) {
        this.note1 = note1;
    }
    public double getNote2() {
        return note2;
    }
    public void setNote2(double note2) {
        this.note2 = note2;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }
}
