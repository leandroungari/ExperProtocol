package experiment;




public class Instituicao extends Element {

    //private int idinstituicao;
    private String nome;
    private String sigla;
    private String pais;

    public Instituicao() {
        super("instituicao");
    }

    public Instituicao(String nome, String sigla, String pais) {
        super("instituicao");
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
    
    
}
