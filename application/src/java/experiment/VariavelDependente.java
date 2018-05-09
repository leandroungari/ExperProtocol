package experiment;



public class VariavelDependente extends Element {
    
    //private int idvariavel_dependente;
    private String var;
    

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

  

    
    
    
}
