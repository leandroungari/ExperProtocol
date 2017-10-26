package experiment;

import bpmn.BusinessProcessDiagram;
import java.util.ArrayList;
import java.util.Calendar;




public class Experimento  extends Element{

    //private int idestudo;
    private String nome;
    private String descricao;
    private String tema;
    private String areatecnica;
    private String tipo;
    private String dominio;
    private String idioma;
    private int status;
    private int replicacao;
    
    private BusinessProcessDiagram diagrama;
    
    private Calendar data_inicial;
    
    private ArrayList<Experimentador> experimentadores = new ArrayList<>();
    
    private Definicao definicao = new Definicao();
    
    private Contexto contexto = new Contexto();
    
    private ArrayList<Hipotese> hipoteses = new ArrayList<>();

    private ArrayList<Validade> validades = new ArrayList<>();
    
    private ArrayList<Metrica> metricas = new ArrayList<>();
    
    private ArrayList<Tecnica> tecnicas = new ArrayList<>();
    
    private ArrayList<Defeito> defeitos = new ArrayList<>();

    private ArrayList<Grupo> grupos = new ArrayList<>();
    
    private Interpretacao interpretacao = new Interpretacao();
    private ArrayList<ArquivoInterpretacao> arquivoInterpretacao = new ArrayList<>();

    public Experimento() {
        
        super("experimento");
    }

    public Experimento(String nome, String descricao, String tema, String areatecnica, String tipo, String dominio, String idioma, int status, int replicacao, Calendar data_inicial) {
        super("experimento");
        this.nome = nome;
        this.descricao = descricao;
        this.tema = tema;
        this.areatecnica = areatecnica;
        this.tipo = tipo;
        this.dominio = dominio;
        this.idioma = idioma;
        this.status = status;
        this.replicacao = replicacao;
        this.data_inicial = data_inicial;
    }

    public BusinessProcessDiagram getDiagrama() {
        return diagrama;
    }

    public void setDiagrama(BusinessProcessDiagram diagrama) {
        this.diagrama = diagrama;
    }

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getAreatecnica() {
        return areatecnica;
    }

    public void setAreatecnica(String areatecnica) {
        this.areatecnica = areatecnica;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReplicacao() {
        return replicacao;
    }

    public void setReplicacao(int replicacao) {
        this.replicacao = replicacao;
    }

    public Calendar getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Calendar data_inicial) {
        this.data_inicial = data_inicial;
    }

    public ArrayList<Experimentador> getExperimentadores() {
        return experimentadores;
    }

    public void setExperimentadores(ArrayList<Experimentador> experimentadores) {
        this.experimentadores = experimentadores;
    }

    public Definicao getDefinicao() {
        return definicao;
    }

    public void setDefinicao(Definicao definicao) {
        this.definicao = definicao;
    }

    public Contexto getContexto() {
        return contexto;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Hipotese> getHipoteses() {
        return hipoteses;
    }

    public void setHipoteses(ArrayList<Hipotese> hipoteses) {
        this.hipoteses = hipoteses;
    }

    public ArrayList<Validade> getValidades() {
        return validades;
    }

    public void setValidades(ArrayList<Validade> validades) {
        this.validades = validades;
    }

    public ArrayList<Metrica> getMetricas() {
        return metricas;
    }

    public void setMetricas(ArrayList<Metrica> metricas) {
        this.metricas = metricas;
    }

    public ArrayList<Tecnica> getTecnicas() {
        return tecnicas;
    }

    public void setTecnicas(ArrayList<Tecnica> tecnicas) {
        this.tecnicas = tecnicas;
    }

    public ArrayList<Defeito> getDefeitos() {
        return defeitos;
    }

    public void setDefeitos(ArrayList<Defeito> defeitos) {
        this.defeitos = defeitos;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Interpretacao getInterpretacao() {
        return interpretacao;
    }

    public void setInterpretacao(Interpretacao interpretacao) {
        this.interpretacao = interpretacao;
    }

    public ArrayList<ArquivoInterpretacao> getArquivoInterpretacao() {
        return arquivoInterpretacao;
    }

    public void setArquivoInterpretacao(ArrayList<ArquivoInterpretacao> arquivoInterpretacao) {
        this.arquivoInterpretacao = arquivoInterpretacao;
    }

    
}
