package experiment;

import bpmn.BusinessProcessDiagram;
import java.util.ArrayList;




public class Experimento  extends Element{

    //private int idestudo;
    private String nome;
    private String descricao;
    private String tema;
    private String areatecnica;
    private String tipo;
    private String dominio;
    private String idioma;
    //private int status;
    private int replicacao;
    
    private BusinessProcessDiagram diagrama;
    
    private ArrayList<Experimentador> experimentadores = new ArrayList<>();
    
    private Definicao definicao = new Definicao();
    
    private ArrayList<Caracterizacao> questoes = new ArrayList<>();
    
    private ArrayList<Hipotese> hipoteses = new ArrayList<>();
    private ArrayList<Cronograma> cronogramas = new ArrayList<>();
    private ArrayList<VariavelDependente> variaveisDependentes = new ArrayList<>();
    private ArrayList<VariavelIndependente> variaveisIndependentes = new ArrayList<>();
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Artefato> artefatos = new ArrayList<>();
    private ArrayList<Validade> validades = new ArrayList<>();
    private ArrayList<Grupo> grupos = new ArrayList<>();
    private ArrayList<Defeito> defeitos = new ArrayList<>();
    private ArrayList<Interpretacao> interpretacoes = new ArrayList<>();
    private ArrayList<Conclusao> conclusoes = new ArrayList<>();

    public Experimento() {
        
        super("experimento");
    }

    public ArrayList<Caracterizacao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(ArrayList<Caracterizacao> questoes) {
        this.questoes = questoes;
    }

    public ArrayList<Interpretacao> getInterpretacoes() {
        return interpretacoes;
    }

    public void setInterpretacoes(ArrayList<Interpretacao> interpretacoes) {
        this.interpretacoes = interpretacoes;
    }

    public ArrayList<Conclusao> getConclusoes() {
        return conclusoes;
    }

    public void setConclusoes(ArrayList<Conclusao> conclusoes) {
        this.conclusoes = conclusoes;
    }

    public ArrayList<Cronograma> getCronogramas() {
        return cronogramas;
    }

    public void setCronogramas(ArrayList<Cronograma> cronogramas) {
        this.cronogramas = cronogramas;
    }

    
    
    public BusinessProcessDiagram getDiagrama() {
        return diagrama;
    }

    public void setDiagrama(BusinessProcessDiagram diagrama) {
        this.diagrama = diagrama;
    }

    public ArrayList<Artefato> getArtefatos() {
        return artefatos;
    }

    public void setArtefatos(ArrayList<Artefato> artefatos) {
        this.artefatos = artefatos;
    }
    
    

    public ArrayList<VariavelDependente> getVariaveisDependentes() {
        return variaveisDependentes;
    }

    public void setVariaveisDependentes(ArrayList<VariavelDependente> variaveisDependentes) {
        this.variaveisDependentes = variaveisDependentes;
    }

    public ArrayList<VariavelIndependente> getVariaveisIndependentes() {
        return variaveisIndependentes;
    }

    public void setVariaveisIndependentes(ArrayList<VariavelIndependente> variaveisIndependentes) {
        this.variaveisIndependentes = variaveisIndependentes;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
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

    

    public int getReplicacao() {
        return replicacao;
    }

    public void setReplicacao(int replicacao) {
        this.replicacao = replicacao;
    }

    /*public Calendar getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Calendar data_inicial) {
        this.data_inicial = data_inicial;
    }*/

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
    
    

    /*public Contexto getContexto() {
        return contexto;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }*/

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

    /*public ArrayList<Metrica> getMetricas() {
        return metricas;
    }

    public void setMetricas(ArrayList<Metrica> metricas) {
        this.metricas = metricas;
    }*/

    /*public ArrayList<Tecnica> getTecnicas() {
        return tecnicas;
    }

    public void setTecnicas(ArrayList<Tecnica> tecnicas) {
        this.tecnicas = tecnicas;
    }*/

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

    
    
}
