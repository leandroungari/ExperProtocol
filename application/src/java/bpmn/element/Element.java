/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.element;

import bpmn.connectionobject.ConnectionObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("Element")
public class Element {


    private String id;

    private int x;

    private int y;
    
    private String description;
    
    private String name;

    private String type;
    

    private ArrayList<Element> elements;

    private ArrayList<ConnectionObject> transitions;
    
    private ArrayList<String> vinculos;

    public Element() {
    }

    public Element(String id, int x, int y, String description, ArrayList<Element> elements, ArrayList<ConnectionObject> transitions, ArrayList<String> vinculos) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.description = description;
        this.elements = elements;
        this.transitions = transitions;
        this.vinculos = vinculos;
    }

    public ArrayList<ConnectionObject> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<ConnectionObject> transitions) {
        this.transitions = transitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getVinculos() {
        return vinculos;
    }

    public void setVinculos(ArrayList<String> vinculos) {
        this.vinculos = vinculos;
    }

    
  

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
