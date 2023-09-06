package com.vitin.RinhaBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Pessoa")
public class Pessoa implements Serializable {

    @Id
    @Column
    private UUID id;
    @Column(nullable = false, length = 32, unique = true)
    String apelido;

    @Column(nullable = false, length = 100)
    String nome;
    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy/MM/dd")
    Date nascimento;
    String[] stack;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String[] getStack() {
        return stack;
    }

    public void setStack(String[] stack) {
        this.stack = stack;
    }

    public Pessoa() {
    }

    public Pessoa(UUID id, String apelido, String nome, Date nascimento, String[] stack) {
        this.id = id;
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }
}
