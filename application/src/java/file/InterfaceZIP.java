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
@XStreamAlias("interface")
public class InterfaceZIP {
    
    private String nome;
    private String caminho;
    
    @XStreamImplicit
    private ArrayList<String> interpretacoes;
    
    @XStreamImplicit
    private ArrayList<String> artefatos;

    
    public InterfaceZIP(String nome, String caminho, ArrayList<String> interpretacoes, ArrayList<String> artefatos) {
        this.nome = nome;
        this.caminho = caminho;
        this.interpretacoes = interpretacoes;
        this.artefatos = artefatos;
    }
    
    public InterfaceZIP(){
        
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

    @Override
    public String toString() {
        
        return "{nome: "+ nome + ", caminho: "+ caminho + ", interpretacoes: " + (interpretacoes != null ? interpretacoes.toString() : "[]") + ", artefatos: " + (artefatos != null ? artefatos.toString() : "[]") + "}";
    }
    
    
    
    
}
