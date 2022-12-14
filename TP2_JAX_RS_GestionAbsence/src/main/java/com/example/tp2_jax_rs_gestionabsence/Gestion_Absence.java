package com.example.tp2_jax_rs_gestionabsence;
import com.example.tp2_jax_rs_gestionabsence.Model.Etudiant;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("Gestion_Absence")
public class Gestion_Absence {
    public static List<Etudiant> etudiants = new ArrayList<Etudiant>() {
        {
            add(new Etudiant("Aboutajedyne", "Moad","cn1", 3, 0.3));
            add(new Etudiant("Lechheb", "Amine", "cn2", 3 , 0.5));
            add(new Etudiant("Bouzit", "Mohammed", "cn3", 3, 0.5));
        }
    };

    @GET
    @Path("/read/{cne}")
    public double read(@PathParam("cne")String cne) {
        List<Etudiant> e = etudiants.stream().filter(etudiant-> etudiant.getCne().equals(cne)).collect(Collectors.toList());
        if(e.size() > 0) {
            return e.get(0).getTaux();
        }
        return -1;
    }
    @GET
    @Path("/read")
    @Produces("application/json")
    public List<Etudiant> read(){
        return etudiants;
    }
    @POST
    @Path("addEtudiant/{cne}")
    @Produces("application/json")
    public Etudiant add(@PathParam("cne") String cne) {
        List<Etudiant> e = etudiants.stream().filter(etudiant-> etudiant.getCne().equals(cne)).collect(Collectors.toList());
        if(e.size() > 0) {
            return e.get(0);
        }
        return null;
    }

    @PUT
    @Path("updateEtudiant")
    @Consumes("application/json")
    public void update(Etudiant e) {
        List<Etudiant> et = etudiants.stream().filter(etudiant-> etudiant.getCne().equals(e.getCne())).collect(Collectors.toList());
        if(et.size() > 0) {
            etudiants.remove(et.get(0));
            etudiants.add(e);
        }
    }

    @DELETE
    @Path("deleteEtudiant/{cne}")
    public void delete(@PathParam("cne")String cne) {
        List<Etudiant> et = etudiants.stream().filter(etudiant-> etudiant.getCne().equals(cne)).collect(Collectors.toList());
        if(et.size() > 0) {
            etudiants.remove(et.get(0));
        }
    }

    @GET
    @Path("blacklistedEtudiant")
    @Produces("application/json")
    public List<Etudiant> blackListCreate(){
        return etudiants.stream().filter(etudiant -> etudiant.getTaux() >= 0.5).sorted(Comparator.comparing(Etudiant::getTaux).thenComparing(Etudiant::getNom)).collect(Collectors.toList());
    }
}
