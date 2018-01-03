package experiment;

import java.util.ArrayList;



public class Hipotese extends Element {

    private String questao;
    private String hipoteseNula;
    private String hipoteseAlternativa;
    //private int idexperimento;
    //private int idhipotese;
    
    //private ArrayList<VariavelDependente> variaveisDependentes = new ArrayList<>();
    //private ArrayList<VariavelIndependente> variaveisIndependentes = new ArrayList<>();
    
    private boolean protegido = false;

    public Hipotese() {
        super("hipotese");
    }

    public Hipotese(String questao, String hipoteseNula, String hipoteseAlternativa) {
        super("hipotese");
        this.questao = questao;
        this.hipoteseNula = hipoteseNula;
        this.hipoteseAlternativa = hipoteseAlternativa;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
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

    /*public ArrayList<VariavelDependente> getVariaveisDependentes() {
        return variaveisDependentes;
    }

    public void setVariaveisDependentes(ArrayList<VariavelDependente> variaveisDependentes) {
        this.variaveisDependentes = variaveisDependentes;
    }

    public ArrayList<VariavelIndependente> getVariaveisIndependentes() {
        return variaveisIndependentes;
    }

    public void setVariaveisIndependentes(ArrayList<VariavelIndependente> variaveisIndependentes) {
        this.variaveisIndependentes = variaveisIndependentes;
    }*/

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
}
