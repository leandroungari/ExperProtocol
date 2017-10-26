package experiment;



public class VariavelDependente extends Element {
    
    //private int idvariavel_dependente;
    private String var;
    //private int estudo_idestudo;
    //private int hipotese;
    private boolean protegido = false;

    public VariavelDependente() {
        super("variavelDependente");
    }

    public VariavelDependente(String var) {
        super("variavelDependente");
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
