package experiment;

import java.util.ArrayList;



//são as estatísticas
public class Interpretacao  extends Element{

    private String id;
    private String resultados;
    
    private ArrayList<ArquivoInterpretacao> arquivoInterpretacao = new ArrayList<>();
    //private int idChaveEx;

    public Interpretacao() {
        super("interpretacao");
    }

    public Interpretacao(String resultados) {
        super("interpretacao");
        this.resultados = resultados;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ArquivoInterpretacao> getArquivoInterpretacao() {
        return arquivoInterpretacao;
    }

    public void setArquivoInterpretacao(ArrayList<ArquivoInterpretacao> arquivoInterpretacao) {
        this.arquivoInterpretacao = arquivoInterpretacao;
    }
    

    
    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }


    
    
}
