/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experiment.factory;

import bpmn.factory.json.Protocol;
import experiment.Experimento;

/**
 *
 * @author leandroungari
 */
public class ExperimentBox {


    private Experimento experimento;
    private Protocol protocol;

    public ExperimentBox() {
    }

    public ExperimentBox(Experimento experimento, Protocol protocol) {
        this.experimento = experimento;
        this.protocol = protocol;
    }

    public Experimento getExperimento() {
        return experimento;
    }

    public void setExperimento(Experimento experimento) {
        this.experimento = experimento;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    
}
