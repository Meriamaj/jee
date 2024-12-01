package com.example;

import com.example.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String AuthHeader = requestContext.getHeaderString("Authorization");

        if (AuthHeader == null || !AuthHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authentifiez-vous avant !").build());
            return;
        }

        String token = AuthHeader.substring("Bearer ".length());

        try {
            Claims claims = JWTUtils.validateToken(token);
            requestContext.setProperty("userId", claims.getSubject());
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("le tooken est invalide ou expiré").build());
        }
    }
}
