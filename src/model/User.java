package model;

public class User extends EntidadeBase {

    private String nome;
    private String email;
    private String cidade;

    public User(int id, String nome, String email, String cidade) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
    }

    @Override
    public void imprimirResumo() {
        System.out.println("Usuário: " + nome + " (" + email + ")");
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCidade() { return cidade; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setCidade(String cidade) { this.cidade = cidade; }
}