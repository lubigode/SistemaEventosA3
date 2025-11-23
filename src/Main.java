import repository.Database;
import view.Menu;

public class Main {

    public static void main(String[] args) {

        // Cria tabelas do banco se não existirem
        Database.inicializar();

        // Inicia o menu
        Menu menu = new Menu();
        menu.iniciar();
    }
}