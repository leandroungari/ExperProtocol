package experiment;

public class Validade extends Element {

    //private int idvalidade;
    //private int estudo_idestudo;
    //private int idtipo_validade;
    private TipoValidade tipo;
    private String validade;
    private boolean protegido = false;

    public Validade() {
        super("validade");
    }

    public Validade(TipoValidade tipo, String validade) {
        super("validade");
        this.tipo = tipo;
        this.validade = validade;
    }

    public TipoValidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoValidade tipo) {
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
