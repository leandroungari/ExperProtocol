package experiment;




public class Experimentador extends Element {

    private String id;;
    private String nome;
    private String email;
    private String senha;
    private String senhanova;

    public Experimentador() {
        super("experimentador");
    }

    public Experimentador(String nome, String email, String senha, String senhanova) {
        super("experimentador");
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.senhanova = senhanova;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhanova() {
        return senhanova;
    }

    public void setSenhanova(String senhanova) {
        this.senhanova = senhanova;
    }

    
  
}
