package experiment;




public class Interpretacao  extends Element{

    //private int id;
    private String resultados;
    //private int idChaveEx;

    public Interpretacao() {
        super("interpretacao");
    }

    public Interpretacao(String resultados) {
        super("interpretacao");
        this.resultados = resultados;
    }

    
    
    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }


    
    
}
