package repository;

import model.Participacao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParticipacaoRepository {

    public Participacao inserir(Participacao p) {
        String sql = "INSERT OR IGNORE INTO participacoes(usuario_id,evento_id,data_confirmacao) VALUES(?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getUsuarioId());
            ps.setInt(2, p.getEventoId());
            ps.setString(3, p.getDataConfirmacao().toString());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return new Participacao(rs.getInt(1), p.getUsuarioId(), p.getEventoId(), p.getDataConfirmacao());
                } else {
                    return buscarPorUsuarioEvento(p.getUsuarioId(), p.getEventoId());
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deletar(int usuarioId, int eventoId) {
        String sql = "DELETE FROM participacoes WHERE usuario_id = ? AND evento_id = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setInt(2, eventoId);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Participacao buscarPorUsuarioEvento(int usuarioId, int eventoId) {
        String sql = "SELECT * FROM participacoes WHERE usuario_id = ? AND evento_id = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setInt(2, eventoId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }

    public List<Participacao> listarPorUsuario(int usuarioId) {
        List<Participacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM participacoes WHERE usuario_id = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return lista;
    }

    private Participacao map(ResultSet rs) throws SQLException {
        return new Participacao(
                rs.getInt("id"),
                rs.getInt("usuario_id"),
                rs.getInt("evento_id"),
                LocalDateTime.parse(rs.getString("data_confirmacao"))
        );
    }
}