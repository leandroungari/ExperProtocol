package experiment;

import java.util.ArrayList;





public class Alternativa extends Element{

    //private int idalternativa;
    private String alternativa;
    
    private ArrayList<Caracterizacao> caracterizacoes = new ArrayList<>();
    //private int caracterizacao_idcaracterizacao;
    private boolean protegido = false;

    public Alternativa(String alternativa) {
        super("alternativa");
        this.alternativa = alternativa;
    }

    public Alternativa() {
        super("arquivoArtefato");
    }

    
    

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    public ArrayList<Caracterizacao> getCaracterizacoes() {
        return caracterizacoes;
    }

    public void setCaracterizacoes(ArrayList<Caracterizacao> caracterizacoes) {
        this.caracterizacoes = caracterizacoes;
    }
    
    
}
