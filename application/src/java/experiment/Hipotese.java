package experiment;

import java.util.ArrayList;



public class Hipotese extends Element {
    
    
    //private String questao;
    private String hipoteseNula;
    private String hipoteseAlternativa;
    //private int idexperimento;
    //private int idhipotese;
    
    //private ArrayList<VariavelDependente> variaveisDependentes = new ArrayList<>();
    //private ArrayList<VariavelIndependente> variaveisIndependentes = new ArrayList<>();
    
    

    public Hipotese() {
        super("hipotese");
    }


    
    


    public String getHipoteseNula() {
        return hipoteseNula;
    }

    public void setHipoteseNula(String hipoteseNula) {
        this.hipoteseNula = hipoteseNula;
    }

    public String getHipoteseAlternativa() {
        return hipoteseAlternativa;
    }

    public void setHipoteseAlternativa(String hipoteseAlternativa) {
        this.hipoteseAlternativa = hipoteseAlternativa;
    }

  
    
    
    
}
