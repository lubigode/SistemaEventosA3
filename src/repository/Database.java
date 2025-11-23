package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:./eventos.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    public static void inicializar() {
        String sqlUsuarios = """
            CREATE TABLE IF NOT EXISTS usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                cidade TEXT
            );
        """;

        String sqlEventos = """
            CREATE TABLE IF NOT EXISTS eventos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                endereco TEXT,
                categoria TEXT,
                horario TEXT,
                descricao TEXT
            );
        """;

        String sqlParticipacoes = """
            CREATE TABLE IF NOT EXISTS participacoes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario_id INTEGER NOT NULL,
                evento_id INTEGER NOT NULL,
                data_confirmacao TEXT,
                FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                FOREIGN KEY (evento_id) REFERENCES eventos(id),
                UNIQUE (usuario_id, evento_id)
            );
        """;

        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sqlUsuarios);
            st.execute(sqlEventos);
            st.execute(sqlParticipacoes);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o banco: " + e.getMessage(), e);
        }
    }
}