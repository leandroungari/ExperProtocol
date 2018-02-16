/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("filelist")
public class FileList {
    
    @XStreamImplicit(itemFieldName = "interpretacao")
    private ArrayList<String> listaInterpretacao;
    @XStreamImplicit(itemFieldName = "artefato")
    private ArrayList<String> listaArtefato;

 

    public FileList() {
        
        this.listaInterpretacao = new ArrayList<>();
        this.listaArtefato = new ArrayList<>();
    }

    public ArrayList<String> getListaInterpretacao() {
        return listaInterpretacao;
    }

    public void setListaInterpretacao(ArrayList<String> listaInterpretacao) {
        this.listaInterpretacao = listaInterpretacao;
    }

    public ArrayList<String> getListaArtefato() {
        return listaArtefato;
    }

    public void setListaArtefato(ArrayList<String> listaArtefato) {
        this.listaArtefato = listaArtefato;
    }
    
    
    
    
}
