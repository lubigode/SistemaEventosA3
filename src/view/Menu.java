package view;

import service.SistemaEventos;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {

    private SistemaEventos sistema = new SistemaEventos();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            System.out.println("\nSISTEMA DE EVENTOS");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Cadastrar evento");
            System.out.println("3 - Listar eventos");
            System.out.println("4 - Listar usuários");
            System.out.println("5 - Confirmar participação em evento");
            System.out.println("6 - Cancelar participação");
            System.out.println("7 - Listar eventos confirmados do usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarEvento();
                case 3 -> sistema.listarEventos();
                case 4 -> listarUsuarios();
                case 5 -> participar();
                case 6 -> cancelar();
                case 7 -> listarEventosConfirmados();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("E-mail: ");
        String email = sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        sistema.cadastrarUsuario(nome, email, cidade);
    }

    private void cadastrarEvento() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        String categoria;
        while (true) {
            sistema.mostrarCategorias();
            System.out.print("Escolha a categoria: ");
            categoria = sc.nextLine();

            if (sistema.categoriaValida(categoria)) {
                break;
            } else {
                System.out.println("Categoria inválida! Tente novamente.\n");
            }
        }

        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        System.out.println("\nData do evento (formato: DIA/MES/ANO): ");
        System.out.print("Ex: 30/01/2025\n");
        String dataStr = sc.nextLine();

        System.out.println("Hora do evento (formato: HH:MM): ");
        System.out.print("Ex: 19:00\n");
        String horaStr = sc.nextLine();

        // Quebra data
        String[] dataPartes = dataStr.split("/");
        int dia = Integer.parseInt(dataPartes[0]);
        int mes = Integer.parseInt(dataPartes[1]);
        int ano = Integer.parseInt(dataPartes[2]);

        // Quebra hora
        String[] horaPartes = horaStr.split(":");
        int hora = Integer.parseInt(horaPartes[0]);
        int minuto = Integer.parseInt(horaPartes[1]);

        LocalDateTime data = LocalDateTime.of(ano, mes, dia, hora, minuto);

        sistema.cadastrarEvento(nome, endereco, categoria, data, desc);
    }

    private void listarUsuarios() {
        System.out.println("LISTA DE USUÁRIOS");

        var usuarios = sistema.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (var u : usuarios) {
            System.out.println("ID: " + u.getId());
            System.out.println("Nome: " + u.getNome());
            System.out.println("E-mail: " + u.getEmail());
            System.out.println("Cidade: " + u.getCidade());
            System.out.println("------------------------");
        }
    }

    private void participar() {
        System.out.print("ID do usuário: ");
        int userId = sc.nextInt();

        System.out.print("ID do evento: ");
        int eventId = sc.nextInt();
        sc.nextLine();

        sistema.confirmarParticipacao(userId, eventId);
    }

    private void cancelar() {
        System.out.print("ID do usuário: ");
        int userId = sc.nextInt();

        System.out.print("ID do evento: ");
        int eventId = sc.nextInt();
        sc.nextLine();

        sistema.cancelarParticipacao(userId, eventId);
    }

    private void listarEventosConfirmados() {
        System.out.print("Digite o ID do usuário: ");
        int userId = Integer.parseInt(sc.nextLine());

        sistema.listarParticipacoesUsuario(userId);
    }
}