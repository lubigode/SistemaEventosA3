package model;

import java.time.LocalDateTime;

public class Evento extends EntidadeBase {

    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    public Evento(int id, String nome, String endereco, String categoria,
                  LocalDateTime horario, String descricao) {

        super(id);
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    @Override
    public void imprimirResumo() {
        System.out.println("Evento: " + nome + " (" + categoria + ") - " + horario);
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setHorario(LocalDateTime horario) { this.horario = horario; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}