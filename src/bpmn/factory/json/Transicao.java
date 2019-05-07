/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.factory.json;

/**
 *
 * @author leandroungari
 */
public class Transicao {
    
    private String id;
    private String origem;
    private String destino;
    private String tipo;
    private int pontoOrigem;
    private int pontoDestino;

    public Transicao() {
    }

    public Transicao(String id, String origem, String destino, String tipo, int pontoOrigem, int pontoDestino) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.tipo = tipo;
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPontoOrigem() {
        return pontoOrigem;
    }

    public void setPontoOrigem(int pontoOrigem) {
        this.pontoOrigem = pontoOrigem;
    }

    public int getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(int pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    @Override
    public String toString() {
    
        String s = "{\"id\": " + this.id 
                + ", \"origem\": " + this.origem
                + ", \"destino\": " + this.destino
                + ", \"pontoOrigem\": " + this.pontoOrigem
                + ", \"pontoDestino\": " + this.pontoDestino
                + "}";
        
        return s;
    }
    
    
}
