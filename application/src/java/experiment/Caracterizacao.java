package experiment;

import java.util.ArrayList;


public class Caracterizacao  extends Element{
    
    
    private String questao;
    
    private ArrayList<Metrica> metricas = new ArrayList<>();
    
    

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

    
    
    
}
