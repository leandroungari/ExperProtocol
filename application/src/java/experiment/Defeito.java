package experiment;




public class Defeito extends Element {

    
    
    private String local;
    private String requisito;
    private String descricao;
    
    private String classeDefeito;
    
 
    

    public Defeito() {
        super("defeito");
    }

  
    
    
    
    
    public String getClasseDefeito() {
        return classeDefeito;
    }

    public void setClasseDefeito(String classeDefeito) {
        this.classeDefeito = classeDefeito;
    }

    
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    

    
}
