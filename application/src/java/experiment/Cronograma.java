package experiment;

import java.util.Calendar;




public class Cronograma extends Element {

    
    private String definicao;
    private String data_inicial;
    private String data_final;
    private String tipo;
    private String tempo;
    
    

    public Cronograma() {
        super("cronograma");
    }

    

    

    public String getTipo() {
        return tipo;
    }

    
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

 
    
    
    
    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public String getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(String data_inicial) {
        this.data_inicial = data_inicial;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }



    
    
    
}
