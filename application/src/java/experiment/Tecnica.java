package experiment;

import java.util.ArrayList;



public class Tecnica extends Element {

    //private int idtecnica;
    private String nome;
    private String descricao;
    private String tipoTecnica;
    
    //private String artefato;
    private ArrayList<Artefato> artefatos = new ArrayList<>();
    private ArrayList<ArquivoTecnica> arquivosTecnica = new ArrayList<>();
    //private int estudo_idestudo;
    private boolean protegido = false;

    public Tecnica() {
        super("tecnica");
    }

    public Tecnica(String nome, String descricao, String tipoTecnica, Artefato artefato) {
        super("tecnica");
        this.nome = nome;
        this.descricao = descricao;
        this.tipoTecnica = tipoTecnica;
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

    public String getTipoTecnica() {
        return tipoTecnica;
    }

    public void setTipoTecnica(String tipoTecnica) {
        this.tipoTecnica = tipoTecnica;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    public ArrayList<Artefato> getArtefatos() {
        return artefatos;
    }

    public void setArtefatos(ArrayList<Artefato> artefatos) {
        this.artefatos = artefatos;
    }

    public ArrayList<ArquivoTecnica> getArquivosTecnica() {
        return arquivosTecnica;
    }

    public void setArquivosTecnica(ArrayList<ArquivoTecnica> arquivosTecnica) {
        this.arquivosTecnica = arquivosTecnica;
    }

    
    
}
