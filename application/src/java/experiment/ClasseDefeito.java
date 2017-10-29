package experiment;


public class ClasseDefeito  extends Element{
    //private int idclasse_defeito;
    private String nome;
    private String descricao;
    //private int estudo_idestudo;
    private boolean protegido = false;

    public ClasseDefeito() {
        super("classeDefeito");
    }

    public ClasseDefeito(String nome, String descricao) {
        super("classeDefeito");
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
}
