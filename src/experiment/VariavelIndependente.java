package experiment;



public class VariavelIndependente extends Element {
    
    private String var;

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

  
    
    
    
}
