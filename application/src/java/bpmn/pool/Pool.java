/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.pool;

import bpmn.connectionobject.ConnectionObject;
import bpmn.element.Element;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("Pool")
public class Pool extends Element{
    
  
    private ArrayList<Lane> lanes;  
    private float width;
    

    
    public Pool() {
    }

    public Pool(String id, float x, float y, float width, String description, ArrayList<Element> elements, ArrayList<ConnectionObject> transitions, ArrayList<String> vinculos) {
        super(id, x, y, description, elements, transitions, vinculos);
    
        this.width = width;
        this.setType("BPMNLane");
        setName("participant");
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    public void setLanes(ArrayList<Lane> lanes) {
        this.lanes = lanes;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }


    
    
}
