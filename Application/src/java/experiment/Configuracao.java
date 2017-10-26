package experiment;

import java.util.ArrayList;



public class Configuracao  extends Element{

    //private int idconfiguracao;
    private String atividade;
    private String definicao;
    //private int grupo_idgrupo;
    //private int tecnica_idtecnica;
    //private int artefato_idartefato;
    //private int cronograma_idcronograma;
    //private int estudo_idestudo;
    
    //private ArrayList<Configuracao> configuracoes = new ArrayList<>();
    private ArrayList<Cronograma> cronogramas = new ArrayList<>();
    private ArrayList<RespostaConfiguracao> respostas = new ArrayList<>();
    //private ArrayList<CaracterizacaoConfiguracao> listaCaracterizacaoConfiguracao = new ArrayList<>();
    //private ArrayList<RespostaConfiguracao> respostasConfiguracao = new ArrayList<>();
    
    
    private boolean protegido = false;

    public Configuracao(String atividade, String definicao) {
        super("configuracao");
        this.atividade = atividade;
        this.definicao = definicao;
    }

    
    
    
    public Configuracao() {
        super("configuracao");
    }

    public ArrayList<Cronograma> getCronogramas() {
        return cronogramas;
    }

    public void setCronogramas(ArrayList<Cronograma> cronogramas) {
        this.cronogramas = cronogramas;
    }

    public ArrayList<RespostaConfiguracao> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<RespostaConfiguracao> respostas) {
        this.respostas = respostas;
    }

    
    
    
    
    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
}
