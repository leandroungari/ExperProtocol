/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
public class FileList {
    
    private ArrayList<File> listaInterpretacao = new ArrayList<>();
    private ArrayList<File> listaArtefato = new ArrayList<>();

    public ArrayList<File> getListaInterpretacao() {
        return listaInterpretacao;
    }

    public void setListaInterpretacao(ArrayList<File> listaInterpretacao) {
        this.listaInterpretacao = listaInterpretacao;
    }

    public ArrayList<File> getListaArtefato() {
        return listaArtefato;
    }

    public void setListaArtefato(ArrayList<File> listaArtefato) {
        this.listaArtefato = listaArtefato;
    }

    public FileList() {
    }
    
    
    
}
