package main.java.com.example;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
            .entity("Utilisateur ou Pokémon introuvable").build();
        }          
        if (! user.Encherir(enchere.getMontantEnch())) {
        return Response.status(Response.Status.BAD_REQUEST)
        .entity("Montant non suffissants").build();
    }
    enchere.setUtilisateur(user);
    enchere.setDate(date);
    enchere.setPokemon(pokemon);
    em.persist(enchere);
    return Response.status(Response.Status.CREATED).entity(enchere).build();
}
}