package experiment;

import java.util.ArrayList;



public class Participante extends Element {

    
    private String grupo;
    private Pessoa pessoa;

    public Participante() {
        super("participante");
    }

    public Participante(Pessoa pessoa) {
        super("participante");
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /*public ArrayList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<Resposta> respostas) {
        this.respostas = respostas;
    }*/
    
    
    
    
}
