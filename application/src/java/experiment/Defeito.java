package experiment;

import java.util.ArrayList;




public class Defeito extends Element {

    //private int iddefeito;
    private String identificacao;
    private String local;
    private String requisito;
    private String descricao;
    
    private ArrayList<ClasseDefeito> classesDefeito = new ArrayList<>();
    
    //private int classe_defeito_idclasse_defeito;
    //private int estudo_idestudo;
    //private int artefato_idartefato;
    private boolean protegido = false;

    public Defeito() {
        super("defeito");
    }

    public Defeito(String identificacao, String local, String requisito, String descricao) {
        super("defeito");
        this.identificacao = identificacao;
        this.local = local;
        this.requisito = requisito;
        this.descricao = descricao;
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

    public ArrayList<ClasseDefeito> getClassesDefeito() {
        return classesDefeito;
    }

    public void setClassesDefeito(ArrayList<ClasseDefeito> classesDefeito) {
        this.classesDefeito = classesDefeito;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
}
