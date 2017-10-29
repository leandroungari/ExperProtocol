package experiment;


public class Contexto extends Element {

    private String contexto;
    private String equipe;
    private String problema;
    private String dominio;
    //private int idexperimento;
    private boolean protegido;
    //private int idcontexto;

    public Contexto() {
        super("contexto");
    }

    public Contexto(String contexto, String equipe, String problema, String dominio, boolean protegido) {
        super("contexto");
        this.contexto = contexto;
        this.equipe = equipe;
        this.problema = problema;
        this.dominio = dominio;
        this.protegido = protegido;
    }

    
    
    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
}
