package br.univille.sistema.entity;

import java.util.Date;

public class Cliente {
    
    // Atributos
    private long id;
    private String name;
    private String cpf;
    private Date birthDate;

    // Construtores
    public Cliente() {
        this.id = -1;
    }

    public Cliente(String nome, String CPF) {
        
        this();
        
        if(!name.equals("") || !CPF.equals("")) {
            this.name = nome;
            this.cpf = CPF;
        }
    }

    // GETTER E SETTER - id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // GETTER E SETTER - name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // GETTER E SETTER - CPF
    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        cpf = CPF;
    }

    // GETTER E SETTER - birthDate
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }   
}
