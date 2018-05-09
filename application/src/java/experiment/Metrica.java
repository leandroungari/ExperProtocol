package experiment;



public class Metrica extends Element {

    private String metrica;
    private String descricao;
    

    public Metrica() {
        super("metrica");
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

 
    
    
}
