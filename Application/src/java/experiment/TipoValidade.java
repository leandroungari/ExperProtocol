package experiment;




public class TipoValidade extends Element{
    //private int idtipo_validade;
    private String tipo;

    public TipoValidade(String tipo) {
        super("tipoValidade");
        this.tipo = tipo;
    }

    public TipoValidade() {
        super("tipoValidade");
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    
}
