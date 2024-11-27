package main.java.com.example;

import java.util.List;

import javax.annotation.processing.Generated;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;
import main.java.example.Enchere;




@Path("/encheres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnchereRest {


    @PersistenceContext
    EntityManager em;  
    
    
    @GET
    public List<Enchere> getListEncheres(){
        return em.createQuery("SELECT e  FROM Enchere e", Enchere.class).getResultList();
    }


    @POST
    @Transactional 

    public Response createEnchere(Enchere enchere) {
        Utilisateur user=em.find(Utilisateur.class,enchere.getUtilisateur().getId());
        Pokemon pokemon=em.find(Pokemon.class,enchere.getPokemon().getId()) ;
        if (user == null || pokemon == null) {
            return Response.status(Response.Status.BAD_REQUEST)
            .entity('utilisateur ou pokemon non trouvée').build();

    }
    if (!Utilisateur.Encherir(enchere.getMontantEnch())) {
        return
    }
}
