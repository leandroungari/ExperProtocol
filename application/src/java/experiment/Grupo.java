package experiment;

import java.util.ArrayList;


public class Grupo extends Element {

    //private int idgrupo;
    private String identificacao;
    private String observacao;
    //private int estudo_idestudo;
    private boolean protegido = false;
    
    //private ArrayList<Participante> listaParticipante = new ArrayList<>();
    //private ArrayList<Configuracao> configuracoes = new ArrayList<>();
    private ArrayList<ArquivoInterpretacao> arquivosInterpretacaoGrupo = new ArrayList<>();
    private ArrayList<Interpretacao> interpretacoes = new ArrayList<>();
    
    
    public Grupo(String identificacao, String observacao) {
        super("grupo");
        this.identificacao = identificacao;
        this.observacao = observacao;
    }

    public Grupo() {
        super("grupo");
    }

    
    
    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    /*public ArrayList<Participante> getListaParticipante() {
        return listaParticipante;
    }

    public void setListaParticipante(ArrayList<Participante> listaParticipante) {
        this.listaParticipante = listaParticipante;
    }*/

    /*public ArrayList<Configuracao> getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(ArrayList<Configuracao> configuracoes) {
        this.configuracoes = configuracoes;
    }*/

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
