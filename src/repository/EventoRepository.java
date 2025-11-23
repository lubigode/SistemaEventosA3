package repository;

import model.Evento;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoRepository {

    public Evento inserir(Evento e) {
        String sql = "INSERT INTO eventos(nome,endereco,categoria,horario,descricao) VALUES(?,?,?,?,?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNome());
            ps.setString(2, e.getEndereco());
            ps.setString(3, e.getCategoria());
            ps.setString(4, e.getHorario().toString());
            ps.setString(5, e.getDescricao());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return new Evento(
                            rs.getInt(1),
                            e.getNome(),
                            e.getEndereco(),
                            e.getCategoria(),
                            e.getHorario(),
                            e.getDescricao()
                    );
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir evento: " + ex.getMessage(), ex);
        }
        return null;
    }

    public Evento buscarPorId(int id) {
        String sql = "SELECT * FROM eventos WHERE id = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEvento(rs);
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public List<Evento> listar() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM eventos ORDER BY horario ASC";

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapEvento(rs));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return lista;
    }

    private Evento mapEvento(ResultSet rs) throws SQLException {
        return new Evento(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("categoria"),
                LocalDateTime.parse(rs.getString("horario")),
                rs.getString("descricao")
        );
    }

    public void deletar(int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
