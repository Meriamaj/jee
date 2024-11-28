package com.example;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

public class AdministrateurRest {
    @Inject
    private EntityManager em;

    @Inject
    private Administrateur administrateur;

    // Endpoint pour créer un utilisateur
    @POST
    //@Path("/utilisateur")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUtilisateur(Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = administrateur.createUtilisateur(
                utilisateur.getNom(),
                utilisateur.getPassword(),
                utilisateur.getLimcoins()
        );
        em.persist(createdUtilisateur); // Persistez l'utilisateur dans la base de données
        return Response.status(Response.Status.CREATED).entity(createdUtilisateur).build();
    }

    // Endpoint pour modifier un utilisateur
    @PUT
    @Path("/utilisateur/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifUtilisateur(@PathParam("id") int id, Utilisateur utilisateur) {
        // Récupérer l'utilisateur de la base de données
        Utilisateur existingUtilisateur = em.find(Utilisateur.class, id);
        if (existingUtilisateur == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Modifier l'utilisateur
        administrateur.modifUtilisateur(existingUtilisateur, utilisateur.getLimcoins());
        return Response.ok(existingUtilisateur).build();
    }

    // Endpoint pour supprimer un utilisateur
    @DELETE
    @Path("/utilisateur/{id}")
    public Response deleteUtilisateur(@PathParam("id") int id) {
        // Récupérer l'utilisateur de la base de données
        Utilisateur existingUtilisateur = em.find(Utilisateur.class, id);
        if (existingUtilisateur == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Supprimer l'utilisateur
        administrateur.deleteUtilisateur(existingUtilisateur);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Endpoint pour récupérer tous les utilisateurs
    @GET
    @Path("/utilisateurs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getAllUtilisateurs() {
        // Retourner tous les utilisateurs
        return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
    }
}
