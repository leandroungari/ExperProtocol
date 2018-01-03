package experiment;

public class Validade extends Element {

    //private int idvalidade;
    //private int estudo_idestudo;
    //private int idtipo_validade;
    private String tipo;
    private String validade;
    private boolean protegido = false;

    public Validade() {
        super("validade");
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }
    
    
}
