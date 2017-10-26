
import bpmn.factory.BPMNFactory;
import bpmn.factory.json.Protocol;
import experiment.Experimento;
import experiment.factory.ExperimentFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leandroungari
 */
public class Pacote {
 
    public static void main(String[] args) {
        
        Experimento e = new Experimento();
        
        ExperimentFactory.setExperimento(e);
        
        ExperimentFactory.setProtocol(new Protocol());
       
        String xml = ExperimentFactory.toXML();
        
        System.out.println(xml);
    }
}
