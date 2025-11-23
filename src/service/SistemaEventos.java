package service;

import model.Evento;
import model.Participacao;
import model.User;
import repository.EventoRepository;
import repository.ParticipacaoRepository;
import repository.UserRepository;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class SistemaEventos {

    private UserRepository usuarioRepo = new UserRepository();
    private EventoRepository eventoRepo = new EventoRepository();
    private ParticipacaoRepository participacaoRepo = new ParticipacaoRepository();
    private static final String[] CATEGORIAS = {
            "Musica",
            "Teatro",
            "Esporte",
            "Cultura",
            "Tecnologia",
            "Palestra"
    };

    public void cadastrarUsuario(String nome, String email, String cidade) {
        usuarioRepo.inserir(new User(0, nome, email, cidade));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void cadastrarEvento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        eventoRepo.inserir(new Evento(0, nome, endereco, categoria, horario, descricao));
        System.out.println("Evento cadastrado com sucesso!");
    }

    public boolean categoriaValida(String categoria) {
        for (String c : CATEGORIAS) {
            if (c.equalsIgnoreCase(categoria)) {
                return true;
            }
        }
        return false;
    }

    public void mostrarCategorias() {
        System.out.println("Categorias permitidas:");
        for (String c : CATEGORIAS) {
            System.out.println("- " + c);
        }
    }

    public void listarEventos() {
        List<Evento> eventos = eventoRepo.listar();

        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        System.out.println("EVENTOS");

        LocalDateTime agora = LocalDateTime.now();

        for (Evento e : eventos) {

            System.out.println("ID: " + e.getId());
            System.out.println("Nome: " + e.getNome());
            System.out.println("Categoria: " + e.getCategoria());
            System.out.println("Data/Hora: " + e.getHorario());

            // CLASSIFICAÇÃO DO EVENTO
            LocalDateTime h = e.getHorario();

            if (h.isBefore(agora)) {
                System.out.println("Status: Passado");
            } else if (
                    h.getYear() == agora.getYear() &&
                            h.getMonth() == agora.getMonth() &&
                            h.getDayOfMonth() == agora.getDayOfMonth() &&
                            h.getHour() == agora.getHour() &&
                            h.getMinute() == agora.getMinute()
            ) {
                System.out.println("Status: Acontecendo");
            } else {
                System.out.println("Status: Futuro");
            }

            System.out.println("---------------------------------------");
        }
    }

        public List<User> listarUsuarios() {
        return usuarioRepo.listar();
    }

    public void imprimirUsuarios() {
        List<User> usuarios = usuarioRepo.listar();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("USUÁRIOS");
        for (User u : usuarios) {
            System.out.println("ID: " + u.getId());
            System.out.println("Nome: " + u.getNome());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Cidade: " + u.getCidade());
            System.out.println("-----------------------------");
        }
    }

    public void confirmarParticipacao(int usuarioId, int eventoId) {
        participacaoRepo.inserir(new Participacao(0, usuarioId, eventoId, LocalDateTime.now()));
        System.out.println("Participação confirmada!");
    }

    public void cancelarParticipacao(int usuarioId, int eventoId) {
        participacaoRepo.deletar(usuarioId, eventoId);
        System.out.println("Participação cancelada.");
    }

    public void listarParticipacoesUsuario(int userId) {
        List<Participacao> participacoes = participacaoRepo.listarPorUsuario(userId);

        if (participacoes.isEmpty()) {
            System.out.println("Nenhuma participação encontrada para este usuário.");
            return;
        }

        System.out.println("EVENTOS CONFIRMADOS");

        for (Participacao p : participacoes) {
            Evento e = eventoRepo.buscarPorId(p.getEventoId());

            if (e != null) {
                System.out.println("Evento: " + e.getNome());
                System.out.println("Categoria: " + e.getCategoria());
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                System.out.println("Data/Hora: " + e.getHorario().format(fmt));
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                System.out.println("Confirmado em: " + p.getDataConfirmacao().format(fmt2));
                System.out.println("------------------------------------");
            }
        }
    }
}