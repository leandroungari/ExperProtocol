package experiment;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;

/**
 * Cada artefato é um tipo de instrumentação
 * 
 */
public class Artefato  extends Element{
    
    
    private String nome;
    private String descricao;
    private String tipoArtefato;
    
    @XStreamImplicit(itemFieldName = "arquivos")
    private ArrayList<ArquivoArtefato> arquivos = new ArrayList<>();
    
    

    public Artefato() {
        super("artefato");
    }

    public Artefato(String nome, String descricao, String tipoArtefato) {
        super("artefato");
        this.nome = nome;
        this.descricao = descricao;
        this.tipoArtefato = tipoArtefato;
    }

    public ArrayList<ArquivoArtefato> getArquivos() {
        return arquivos;
    }

    public void setArquivos(ArrayList<ArquivoArtefato> arquivos) {
        this.arquivos = arquivos;
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

    

    public String getTipoArtefato() {
        return tipoArtefato;
    }

    public void setTipoArtefato(String tipoArtefato) {
        this.tipoArtefato = tipoArtefato;
    }
    
}
