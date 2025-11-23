package model;

import java.time.LocalDateTime;

public class Participacao extends EntidadeBase {

    private int usuarioId;
    private int eventoId;
    private LocalDateTime dataConfirmacao;

    public Participacao(int id, int usuarioId, int eventoId, LocalDateTime dataConfirmacao) {
        super(id); // herda id da classe abstrata
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
        this.dataConfirmacao = dataConfirmacao;
    }

    @Override
    public void imprimirResumo() {
        System.out.println("Participação | Usuário: " + usuarioId +
                " | Evento: " + eventoId +
                " | Data: " + dataConfirmacao);
    }

    public int getUsuarioId() { return usuarioId; }
    public int getEventoId() { return eventoId; }
    public LocalDateTime getDataConfirmacao() { return dataConfirmacao; }

    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public void setEventoId(int eventoId) { this.eventoId = eventoId; }
    public void setDataConfirmacao(LocalDateTime dataConfirmacao) { this.dataConfirmacao = dataConfirmacao; }
}