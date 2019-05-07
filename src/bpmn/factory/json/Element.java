/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.factory.json;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("element")
public class Element {
    
    private String id;
    private float x;
    private float y;
    
    private String type;
    private String name;
    
    private float width;
    private float height;
    
    private String description;
    @XStreamImplicit
    private ArrayList<Element> elements;
    @XStreamImplicit
    private ArrayList<Transicao> transitions;
    @XStreamImplicit
    private ArrayList<String> vinculos;

    public Element(String id, float x, float y, String type, String name, float width, float height, String description, ArrayList<Element> elements, ArrayList<Transicao> transitions, ArrayList<String> vinculos) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = width;
        this.height = height;
        this.name = name;
        this.description = description;
        this.elements = elements;
        this.transitions = transitions;
        this.vinculos = vinculos;
    }

    public Element() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Element> getElements() {
        return elements;
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

    public ArrayList<Transicao> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transicao> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<String> getVinculos() {
        return vinculos;
    }

    public void setVinculos(ArrayList<String> vinculos) {
        this.vinculos = vinculos;
    }

    
    
    
    @Override
    public String toString() {
    
        String s = "";
        s += "{\"id\": " + this.id
                + ", \"x\": " + this.x
                + ", \"y\": " + this.y
                + ", \"type\": " + this.type
                + ", \"name\": " + this.name
                + ", \"width\": " + this.width
                + ", \"height\": " + this.height
                + ", \"description\": " + this.description
                + ", \"elements\": " + (this.elements != null ? this.elements.toString() : "[]")
                + ", \"transitions\": " + (this.transitions != null ? this.transitions.toString() : "[]")
                + ", \"vinculos\": " + (this.vinculos != null ? this.vinculos.toString() : "[]")
                + "}";
        
        return s;
    }
    
    
    
    
}
