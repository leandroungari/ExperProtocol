package experiment;

import java.util.ArrayList;


public class Caracterizacao  extends Element{
    
    //private int idcaracterizacao;
    private String questao;
    
    private ArrayList<Metrica> metricas = new ArrayList<>();
    
    
    //private ArrayList<Configuracao> configuracoes = new ArrayList<>();
    private boolean protegido = false;

    public Caracterizacao() {
        super("caracterizacao");
    }

    public Caracterizacao(String questao, String tipo) {
        super("caracterizacao");
        this.questao = questao;
        //this.tipo = tipo;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }
    
    /*
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }*/

    public ArrayList<Metrica> getMetricas() {
        return metricas;
    }

    public void setMetricas(ArrayList<Metrica> metricas) {
        this.metricas = metricas;
    }

    /*public ArrayList<Configuracao> getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(ArrayList<Configuracao> configuracoes) {
        this.configuracoes = configuracoes;
    }*/
    
    

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
}
