/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote;

import file.Arquivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandroungari
 */
public class PacoteLaboratorio {
    
    public static ArrayList<File> interpretacao = new ArrayList<>();
    public static ArrayList<File> artefato = new ArrayList<>();

    public static ArrayList<File> getInterpretacao() {
        return interpretacao;
    }

    public static void setInterpretacao(ArrayList<File> interpretacao) {
        PacoteLaboratorio.interpretacao = interpretacao;
    }

    public static ArrayList<File> getArtefato() {
        return artefato;
    }

    public static void setArtefato(ArrayList<File> artefato) {
        PacoteLaboratorio.artefato = artefato;
    }
    
    public static void transferirArquivos(String pasta) {
        
        for(File f: interpretacao) {
            
            try {
                Arquivo.upload(pasta + "/interpretacao_arquivos", f.getName(), new FileInputStream(f));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PacoteLaboratorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(File f: artefato) {
            
            try {
                Arquivo.upload(pasta + "/artefato_arquivos", f.getName(), new FileInputStream(f));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PacoteLaboratorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
