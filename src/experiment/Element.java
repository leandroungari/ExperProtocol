/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experiment;

/**
 *
 * @author leandroungari
 */
public class Element {
    
    
    private static int NUM = 0;
    private String id;

    public Element() {
        
        
    }

    public Element(String type) {
        
        
        if (this.id == null || this.id.equals("")) {
            this.id = type + NUM;
            Element.NUM++;
        }
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
