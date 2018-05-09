/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.pool;

import bpmn.connectionobject.ConnectionObject;
import bpmn.element.Element;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("Lane")
public class Lane extends Element{

    private float height;

    
    public Lane() {
    }

    public Lane(String id, float x, float y, float height, String description, ArrayList<Element> elements, ArrayList<ConnectionObject> transitions, ArrayList<String> vinculos) {
        super(id, x, y, description, elements, transitions, vinculos);
        this.height = height;
        this.setType("BPMNLane");
        setName("lane-none");
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    
    
    
    
}
