/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author leandroungari
 */
@XStreamAlias("filepath")
public class FilePath {

    private String caminho;

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public FilePath(String caminho) {
        this.caminho = caminho;
    }

    public FilePath() {
    }
    
    
}
