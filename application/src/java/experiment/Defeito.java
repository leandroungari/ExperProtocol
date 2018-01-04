package experiment;




public class Defeito extends Element {

    private int iddefeito;
    private String identificacao;
    private String local;
    private String requisito;
    private String descricao;
    
    private String classeDefeito;
    
 
    private boolean protegido = false;

    public Defeito() {
        super("defeito");
    }

    public int getIddefeito() {
        return iddefeito;
    }

    public void setIddefeito(int iddefeito) {
        this.iddefeito = iddefeito;
    }

    
    
    public String getClasseDefeito() {
        return classeDefeito;
    }

    public void setClasseDefeito(String classeDefeito) {
        this.classeDefeito = classeDefeito;
    }

    
    
    
    
    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
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

    

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
}
