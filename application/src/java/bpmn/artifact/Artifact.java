/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.artifact;

import bpmn.connectionobject.ConnectionObject;
import bpmn.element.Element;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("Artifact")
public class Artifact extends Element{
    
    

    public Artifact() {
   
    }

    public Artifact(String id, float x, float y, String description, ArrayList<Element> elements, ArrayList<ConnectionObject> transitions, ArrayList<String> vinculos) {
        super(id, x, y, description, elements, transitions, vinculos);
        
        this.setType("BPMNData");
     
    }
    
    
}
