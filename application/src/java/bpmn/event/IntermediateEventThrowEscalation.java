/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.event;

import bpmn.connectionobject.ConnectionObject;
import bpmn.element.Element;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("IntermediateEventThrowEscalation")
public class IntermediateEventThrowEscalation extends IntermediateEvent{

    public IntermediateEventThrowEscalation() {
    }

    public IntermediateEventThrowEscalation(String id, float x, float y, String description, ArrayList<Element> elements, ArrayList<ConnectionObject> transitions, ArrayList<String> vinculos) {
        super(id, x, y, description, elements, transitions, vinculos);
        setName("floatermediate-event-throw-escalation");
    }
    
    
}
