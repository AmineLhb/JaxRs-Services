package com.example.tp2_jax_rs;
import com.example.tp2_jax_rs.Model.Etudiant;
import com.google.gson.Gson;
import javax.ws.rs.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Path("Gestion_Note")
public class Gestion_Note {
    public static List<Etudiant> etudiants = new ArrayList<Etudiant>() {
        {
            add(new Etudiant("Aboutajedyne", "Moad","cn1", 20, 18));
            add(new Etudiant("Lechheb", "Amine","cn2", 18, 18));
            add(new Etudiant("Bouzit", "Mohammed","cn3", 19, 18));
        }
    };
    @GET
    @Path("getEtudiants")
    @Produces("application/json")
    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    @POST
    @Path("/addEtudiant")
    @Produces("application/json")
    @Consumes("application/json")
    public void addEtudiants(Etudiant etudiant) {
        etudiants.add(etudiant);
    }

    @GET
    @Path("getNote/{nom}")
    @Produces("application/json")
    public List<Double> getNote(@PathParam("nom") String nom) {
        for (Etudiant etudiant : etudiants) {

            if (etudiant.getNom().equals(nom)) {
                return new ArrayList<Double>() {
                    {
                        add(etudiant.getNote1());
                        add(etudiant.getNote2());
                    }
                };
            }
        }
        return null;
    }

    @GET
    @Path("getEtudiantValid")
    @Produces("application/json")
    public List<Etudiant> getEtudiantValid() {
        List<Etudiant> etudiantsValid = new ArrayList<>();
        for (Etudiant etudiant : etudiants) {
            if (etudiant.isValid()) {
                etudiantsValid.add(etudiant);
            }
        }
        return etudiantsValid;
    }


    @GET
    @Path("getMajorant")
    @Produces("application/json")
    public Etudiant getMajorant() {
        double moyen = 0;
        Etudiant e = null;
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getMoyen() > moyen) {
                moyen = etudiant.getMoyen();
                e = etudiant;
            }
        }
        return e;
    }

    @GET
    @Path("trierEtudiants")
    @Produces("application/json")
    public List<Etudiant> trierEtudiants(){
        etudiants.sort(Comparator.comparing(Etudiant::getMoyen));
        return etudiants;
    }

    @GET
    @Path("getTaux/{cne}")
    public double taux(@PathParam("cne") String cne) throws RemoteException {
        GestionAbsenceService absence = new GestionAbsenceService("read/" + cne);
        Double taux = new Gson().fromJson(absence.finishAndReturn().readEntity(String.class), Double.class);
        return taux;
    }

    @GET
    @Path("getNoteFinale/{cne}")
    @Produces("application/json")
    public Double getNoteFinale(@PathParam("cne") String cne) throws RemoteException {
        for (Etudiant etudiant : etudiants) {
            GestionAbsenceService absenceService = new GestionAbsenceService("read/" + etudiant.getCne());
            Double absence = new Gson().fromJson(absenceService.finishAndReturn().readEntity(String.class), Double.class);
            if (etudiant.getCne().equals(cne)) {
                double noteFinale = etudiant.getMoyen()*(1-absence) ;
                return noteFinale;
            }
        }
        return null;
    }

//    @GET
//    @Path("getNoteFinale/{nom}")
//    public Double getNoteFinale(@PathParam("nom") String nom) {
//        List<Etudiant> e = etudiants.stream().filter(etudiant -> etudiant.getNom().equals(nom)).collect(Collectors.toList());
//        if (e.size() > 0) {
//            Etudiant et = e.get(0);
//            GestionAbsenceService absenceService = new GestionAbsenceService("read/" + et.getCne());
//            Double taux = new Gson().fromJson(absenceService.finishAndReturn().readEntity(String.class), Double.class);
//            if (taux == -1) {
//                return null;
//            } else {
//                return et.getMoyen() * (1 - taux);
//            }
//        }
//        return null;
//    }

}
