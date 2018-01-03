package experiment;

import java.util.Calendar;




public class Cronograma extends Element {

    private int idcronograma;
    private String definicao;
    private Calendar data_inicial;
    private Calendar data_final;
    private String tipo;
    private String tempo;
    //private int estudo_idestudo;
    private boolean protegido = false;

    public Cronograma() {
        super("cronograma");
    }

    public int getIdcronograma() {
        return idcronograma;
    }

    public void setIdcronograma(int idcronograma) {
        this.idcronograma = idcronograma;
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

    public Calendar getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Calendar data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Calendar getData_final() {
        return data_final;
    }

    public void setData_final(Calendar data_final) {
        this.data_final = data_final;
    }


    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
}
