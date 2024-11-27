package com.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Authentification {

    //private static final Logger logger = LoggerFactory.getLogger(UtilisateurResource.class);

    @PersistenceContext
    EntityManager em;




    @POST
    @Path("/login")
    public Response login(LoginRequest request){
        try {
            Utilisateur user = em.createQuery(
                "SELECT user FROM Utilisateur user WHERE user.nom =: nom AND user.mdp =: mdp",Utilisateur.class).setParameter("nom", request.getNom())
                .setParameter("mot de passe", request.getMdp)
                .getSingleResult();

            if (user != null) {
                return Response.ok(user).build();
            }
            
        } catch (Exception exp) {

            exp.printStackTrace();
        }

        return Response.status(Response.Status.UNAUTHORIZED).entity("nom ou mdp incorrect !").build();

    }

    @POST
    @Path("/Register")
    @Transactional

    public Response register(Utilisateur user){
        long query= em.createQuery("SELECT COUNT(user) from Utilisateur user WHERE u.nom = : nom", Long.class)
        .setParameter("nom", user.getNom())
        .getSingleResult();

        if (query>0)  {
            return Response.status(Response.Status.CONFLICT).entity("nom existe déjà").build();

        }

        em.persist(user);
        return Response.status(Response.Status.CREATED).entity(user).build();


    }



}
