
import bpmn.factory.BPMNFactory;
import bpmn.factory.json.Protocol;
import experiment.factory.ExperimentFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leandroungari
 */
public class AbrirPacote {


    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner servlet = new Scanner(new File("../experimento-vazio.xml"));
        
        String xml = "";
        while(servlet.hasNextLine()){
            xml+=servlet.nextLine();
        }
        
        servlet.close();
        
        ExperimentFactory.fromXML(xml);
        
        Protocol p = BPMNFactory.export(ExperimentFactory.getExperimento().getDiagrama());
        
        ExperimentFactory.setProtocol(p);
        
        String json = ExperimentFactory.export();
        
        System.out.println(json);
    }
}
