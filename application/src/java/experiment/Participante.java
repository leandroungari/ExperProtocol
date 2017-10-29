package experiment;

import java.util.ArrayList;



public class Participante extends Element {

    //private int idparticipante;
    //private int pessoa_idpessoa;
    //private int estudo_idestudo;
    //private int grupo_idgrupo;
    
    private ArrayList<Resposta> respostas = new ArrayList<>();

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

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ArrayList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<Resposta> respostas) {
        this.respostas = respostas;
    }
    
    
    
    
}
