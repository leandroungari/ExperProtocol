package experiment;

import java.util.ArrayList;


public class Caracterizacao  extends Element{
    
    //private int idcaracterizacao;
    private String questao;
    private String tipo;
    //private int estudo_idestudo;
    
    private ArrayList<Configuracao> configuracoes = new ArrayList<>();
    private boolean protegido = false;

    public Caracterizacao() {
        super("caracterizacao");
    }

    public Caracterizacao(String questao, String tipo) {
        super("caracterizacao");
        this.questao = questao;
        this.tipo = tipo;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
}
