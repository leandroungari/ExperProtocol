package experiment;



public class Metrica extends Element {
    
    //private int idmetrica;
    //private int idexperimento;
    private String metrica;
    private String descricao;
    private boolean protegido = false;

    public Metrica() {
        super("metrica");
    }

    public Metrica(String metrica, String descricao) {
        super("metrica");
        this.metrica = metrica;
        this.descricao = descricao;
    }

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
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
