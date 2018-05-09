/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn;

import bpmn.element.Element;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;


@XStreamAlias("BusinessProcessDiagram")
public class BusinessProcessDiagram {

    
    private ArrayList<Element> elements;
    
    private int width;
    private int height;
    
    /*private String name;
    private String version;
    private String author;
    private String language;
    private Calendar creationDate;
    private Calendar modificationDate;*/

    public BusinessProcessDiagram(int width, int height) {
        this.elements = new ArrayList<>();
        this.width = width;
        this.height = height;
    }
    
    

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    
    
}
