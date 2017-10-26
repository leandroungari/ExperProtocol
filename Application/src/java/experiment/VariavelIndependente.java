package experiment;



public class VariavelIndependente extends Element {
    //private int idvariavel_independente;
    private String var;
    //private int hipotese;
    //private int estudo_idestudo;
    private boolean protegido = false;

    public VariavelIndependente() {
        super("variavelIndependente");
    }

    public VariavelIndependente(String var) {
        super("variavelIndependente");
        this.var = var;

    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

   

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
}
