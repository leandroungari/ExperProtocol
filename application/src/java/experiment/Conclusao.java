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
public class Conclusao extends Element{
    
    
    private String conclusao;

    public Conclusao() {
        
        super("conclusao");
    }


    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }
    
    
}
