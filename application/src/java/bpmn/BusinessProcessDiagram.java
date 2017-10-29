/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn;

import bpmn.element.Element;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;
import java.util.Calendar;


@XStreamAlias("BusinessProcessDiagram")
public class BusinessProcessDiagram {

    
    private ArrayList<Element> elements;
    /*private String name;
    private String version;
    private String author;
    private String language;
    private Calendar creationDate;
    private Calendar modificationDate;*/

    public BusinessProcessDiagram() {
        this.elements = new ArrayList<>();
    }
    
    

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    
    
}
