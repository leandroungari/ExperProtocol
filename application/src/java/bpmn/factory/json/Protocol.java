/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.factory.json;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */

@XStreamAlias("protocol")
public class Protocol {

    @XStreamImplicit
    private ArrayList<Element> elements;

    private int width;
    private int height;
    
    public Protocol() {
        
        this.elements = new ArrayList<>();
    }

    public Protocol(ArrayList<Element> elements) {
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
    
    

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
    /*
        String s = "{\"elements\": " + this.elements.toString()
                + "}";
        
        return s;
      */
    
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias("protocol", Protocol.class);
        xstream.autodetectAnnotations(true);
        return xstream.toXML(this);
        
    }
    
    
}
