package com.miguelmf.study.rest.security.model;

public class User {
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
