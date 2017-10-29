/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.connectionobject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("ConnectionObject")
public class ConnectionObject {
    
    private String id;
    private String origem;
    private String destino;
    


    private String tipo;
    
    private int pontoOrigem;
    private int pontoDestino;

    public ConnectionObject(String id, String origem, String destino, int pontoOrigem, int pontoDestino) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
    }

    public ConnectionObject() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String type) {
        this.tipo = type;
    }

   
    
    
    
    
    
}
