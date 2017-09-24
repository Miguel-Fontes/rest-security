package com.miguelmf.study.rest.security.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TarefaRepositorio {

    private Map<Long, Tarefa> tarefas = new HashMap<>();
    private static Long idCounter;

    public TarefaRepositorio() {
        idCounter = idCounter == null ? 0 : idCounter;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idCounter++);
        tarefa.setTitulo("Testar Segurança Rest");
        tarefa.setCompleta(false);

        tarefas.put(tarefa.getId(), tarefa);
    }

    public Collection<Tarefa> listar() {
        return tarefas.values();
    }




}
