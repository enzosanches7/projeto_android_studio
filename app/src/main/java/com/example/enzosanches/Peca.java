package com.example.enzosanches;

import com.google.firebase.firestore.Exclude;

public class Peca {

    @Exclude
    private String id; // ID do documento no Firestore (usado para deletar)
    private String nome;
    private String categoria;
    private String preco;
    private String userId; // Vinculo do usuário

    // Construtor vazio obrigatório para o Firebase
    public Peca() {}

    public Peca(String nome, String categoria, String preco, String userId) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.userId = userId;
    }

    @Exclude
    public String getId() { return id; }
    @Exclude
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getPreco() { return preco; }
    public void setPreco(String preco) { this.preco = preco; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}