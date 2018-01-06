package experiment;

import java.util.ArrayList;


public class Grupo extends Element {

    
    private String observacao;
 
    private ArrayList<ArquivoInterpretacao> arquivosInterpretacaoGrupo = new ArrayList<>();
    private ArrayList<Interpretacao> interpretacoes = new ArrayList<>();
    
    
   
    public Grupo() {
        super("grupo");
    }


    

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

   

    public ArrayList<ArquivoInterpretacao> getArquivosInterpretacaoGrupo() {
        return arquivosInterpretacaoGrupo;
    }

    public void setArquivosInterpretacaoGrupo(ArrayList<ArquivoInterpretacao> arquivosInterpretacaoGrupo) {
        this.arquivosInterpretacaoGrupo = arquivosInterpretacaoGrupo;
    }

    public ArrayList<Interpretacao> getInterpretacoes() {
        return interpretacoes;
    }

    public void setInterpretacoes(ArrayList<Interpretacao> interpretacoes) {
        this.interpretacoes = interpretacoes;
    }
    
    
}
