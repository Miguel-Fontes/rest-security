package com.miguelmf.study.rest.security.endpoints;


import com.miguelmf.study.rest.security.model.Tarefa;
import com.miguelmf.study.rest.security.model.TarefaRepositorio;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("tarefas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarefasResource {

    private final TarefaRepositorio repositorio;

    public TarefasResource() {
        this.repositorio = new TarefaRepositorio();
    }

    @GET
    public Response listar() {
        return Response.ok(repositorio.listar()).build();
    }

}
