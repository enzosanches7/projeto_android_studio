package com.example.enzosanches; // CONFIRA SE ESTE PACOTE ESTÁ IGUAL AO SEU DO APP

public class Peca {
    private String nome;
    private String categoria;
    private String preco;

    // O Firebase precisa de um construtor vazio para funcionar, não apague!
    public Peca() {
    }

    public Peca(String nome, String categoria, String preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getPreco() { return preco; }
    public void setPreco(String preco) { this.preco = preco; }
}