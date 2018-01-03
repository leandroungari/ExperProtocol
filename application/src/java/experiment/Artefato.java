package experiment;

import java.util.ArrayList;

/**
 * Cada artefato é um tipo de instrumentação
 * 
 */
public class Artefato  extends Element{
    
    //private int idartefato;
    private String nome;
    private String descricao;
    private String tipoArtefato;
    
    private ArrayList<ArquivoArtefato> arquivosArtefato = new ArrayList<>();
    
    //private int estudo_idestudo;
    private boolean protegido = false;

    public Artefato() {
        super("artefato");
    }

    public Artefato(String nome, String descricao, String tipoArtefato) {
        super("artefato");
        this.nome = nome;
        this.descricao = descricao;
        this.tipoArtefato = tipoArtefato;
    }

    public ArrayList<ArquivoArtefato> getArquivosArtefato() {
        return arquivosArtefato;
    }

    public void setArquivosArtefato(ArrayList<ArquivoArtefato> arquivosArtefato) {
        this.arquivosArtefato = arquivosArtefato;
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

    
    public boolean isProtegido() {
        return protegido;
    }

    
    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    public String getTipoArtefato() {
        return tipoArtefato;
    }

    public void setTipoArtefato(String tipoArtefato) {
        this.tipoArtefato = tipoArtefato;
    }
    
}
