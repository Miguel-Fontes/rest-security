package com.miguelmf.study.rest.security.endpoints;

import com.miguelmf.study.rest.security.auth.Autorizador;
import com.miguelmf.study.rest.security.model.TarefaRepositorio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tarefas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarefasResource {

    private final TarefaRepositorio repositorio;
    private final Autorizador autorizador = new Autorizador();

    public TarefasResource() {
        this.repositorio = new TarefaRepositorio();
    }

    @GET
    public Response listar(@HeaderParam("Authorization") String token) {
        System.out.println(token);
        autorizador.verifica(token);
        return Response.ok(repositorio.listar()).build();
    }

}
