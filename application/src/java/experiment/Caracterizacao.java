package experiment;

import java.util.ArrayList;


public class Caracterizacao  extends Element{
    
    private String id;
    private String questao;
    
    private ArrayList<Metrica> metricas = new ArrayList<>();
    
    

    public Caracterizacao() {
        super("caracterizacao");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
