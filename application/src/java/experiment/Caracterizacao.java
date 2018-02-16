package experiment;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;


public class Caracterizacao  extends Element{
    
    //private String id;
    private String questao;
    
    @XStreamImplicit(itemFieldName = "metricas")
    private ArrayList<Metrica> metricas = new ArrayList<>();
    
    @XStreamImplicit(itemFieldName = "objetivos")
    private ArrayList<Objetivo> objetivos = new ArrayList<>();

    public Caracterizacao() {
        super("caracterizacao");
    }

    public ArrayList<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(ArrayList<Objetivo> objetivos) {
        this.objetivos = objetivos;
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
