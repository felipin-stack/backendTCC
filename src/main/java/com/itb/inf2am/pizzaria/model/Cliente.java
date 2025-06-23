package com.itb.inf2am.pizzaria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 255, unique = true)
    private String senha;

    @Column(nullable = true, length = 8, unique = true)
    private String cep;

    @Column(nullable = true, length = 20, unique = true)
    private String telefone;


    @Transient
    private String message = "";

    @Transient
    private boolean isValid = true;

    // Construtor padrão
    public Cliente() {}

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   //NOME
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   //EMAIL
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   //SENHA
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
   //CEP
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
//TELEFONE
public String getTelefone() {
    return telefone;
}

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    //message
    public String getMessage() {
        return message;
    }

    public boolean validarCliente() {
        // Implementar validação real aqui
        return isValid;
    }
}

