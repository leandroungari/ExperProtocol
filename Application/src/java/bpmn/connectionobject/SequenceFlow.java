/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.connectionobject;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("SequenceFlow")
public class SequenceFlow extends ConnectionObject{

    public SequenceFlow(String id, String origem, String destino, int pontoOrigem, int pontoDestino) {
        super(id, origem, destino, pontoOrigem, pontoDestino);
        this.setTipo("sequence-flow");
    }

    public SequenceFlow() {
    }

    
    
}
