package com.miguelmf.study.rest.security.endpoints;

import com.miguelmf.study.rest.security.auth.Autorizador;
import com.miguelmf.study.rest.security.auth.Token;
import com.miguelmf.study.rest.security.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Authorization {

    private final Autorizador autorizador = new Autorizador();

    @POST
    public Token autoriza(User user) {
        return autorizador.geraToken(user.getNome());
    }
}
