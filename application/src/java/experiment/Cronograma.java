package experiment;

import java.util.Calendar;




public class Cronograma extends Element {

    //private int idcronograma;
    private String definicao;
    private Calendar data_inicial;
    private Calendar data_final;
    private int tipo;
    private Calendar tempo;
    //private int estudo_idestudo;
    private boolean protegido = false;

    public Cronograma() {
        super("cronograma");
    }

    public Cronograma(String definicao, Calendar data_inicial, Calendar data_final, int tipo, Calendar tempo) {
        super("cronograma");
        this.definicao = definicao;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Calendar getTempo() {
        return tempo;
    }

    public void setTempo(Calendar tempo) {
        this.tempo = tempo;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
}
