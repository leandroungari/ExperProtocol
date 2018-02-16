/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author leandroungari
 */
public class LabPackage {
    
    private String nome;
    private String caminho;
    
    private ArrayList<String> interpretacoes;
    private ArrayList<String> artefatos;
    
    public static LabPackage getInstance(InterfaceZIP zip){
        
        return new LabPackage(zip.getNome().split(Pattern.quote("."))[0], zip.getCaminho(), zip.getInterpretacoes(), zip.getArtefatos());
    }
    
    private LabPackage(String nome, String caminho, ArrayList<String> interpretacoes, ArrayList<String> artefatos){
        
        this.nome = nome;
        this.caminho = caminho;
        this.interpretacoes = interpretacoes;
        this.artefatos = artefatos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public ArrayList<String> getInterpretacoes() {
        return interpretacoes;
    }

    public void setInterpretacoes(ArrayList<String> interpretacoes) {
        this.interpretacoes = interpretacoes;
    }

    public ArrayList<String> getArtefatos() {
        return artefatos;
    }

    public void setArtefatos(ArrayList<String> artefatos) {
        this.artefatos = artefatos;
    }
    
    
}
