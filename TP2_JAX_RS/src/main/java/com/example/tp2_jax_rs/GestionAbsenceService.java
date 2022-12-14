package com.example.tp2_jax_rs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class GestionAbsenceService {
    WebTarget target;
    Form form;
    public GestionAbsenceService(String path) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/TP2_JAX_RS_GestionAbsence_war_exploded/api/Gestion_Absence/"+path);
        this.target = target;
        Form f = new Form();
        this.form = f;

    }

    public void addParam(String name, Object object){
        form.param(name, String.valueOf(object));
    }

    public Response finishAndReturn(){
        return target.request().get();
    }
}
