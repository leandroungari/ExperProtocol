package experiment;

import java.util.ArrayList;



public class Resposta extends Element {

    //private int idresposta;
    private String resposta;
    //private int participante_idparticipante;
    //private int alternativa_idalternativa;
    //private int caracterizacao_idcaracterizacao;

    
    private ArrayList<Alternativa> alternativas = new ArrayList<>();

    public Resposta() {
        super("resposta");
    }

    public Resposta(String resposta) {
        super("resposta");
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public ArrayList<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }
    
    
}
