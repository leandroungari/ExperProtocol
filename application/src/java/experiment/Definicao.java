    package experiment;



//definição do gqm
public class Definicao  extends Element{

    //private int iddefinicao;
    private String objetivo;
    //private String analisar;
    //proposito
    private String finalidade;
    //enfoque
    private String respeito;
    
    private String pontodevista;
    private String contexto;
    //private int estudo_idestudo;
    private boolean protegido = false;

    public Definicao() {
        super("definicao");
    }

    public Definicao(String objetivo, String finalidade, String respeito, String pontodevista, String contexto) {
        super("definicao");
        this.objetivo = objetivo;
        //this.analisar = analisar;
        this.finalidade = finalidade;
        this.respeito = respeito;
        this.pontodevista = pontodevista;
        this.contexto = contexto;
    }

    
    
    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    /*public String getAnalisar() {
        return analisar;
    }

    public void setAnalisar(String analisar) {
        this.analisar = analisar;
    }*/

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getRespeito() {
        return respeito;
    }

    public void setRespeito(String respeito) {
        this.respeito = respeito;
    }

    public String getPontodevista() {
        return pontodevista;
    }

    public void setPontodevista(String pontodevista) {
        this.pontodevista = pontodevista;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
}
