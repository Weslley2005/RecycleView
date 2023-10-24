package br.unigran.novorecycle;

public class Pessoa {
    public String nome;
    public String telefone;
    public Float avaliacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Pessoa() {
        this.nome = nome;
        this.telefone = telefone;
    }
}
