package experiment;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;



//são as estatísticas
public class Interpretacao  extends Element{

    
    private String resultados;
    
    @XStreamImplicit(itemFieldName = "arquivos")
    private ArrayList<ArquivoInterpretacao> arquivos = new ArrayList<>();
    //private int idChaveEx;

    public Interpretacao() {
        super("interpretacao");
    }

    public Interpretacao(String resultados) {
        super("interpretacao");
        this.resultados = resultados;
    }

    public ArrayList<ArquivoInterpretacao> getArquivos() {
        return arquivos;
    }

    public void setArquivos(ArrayList<ArquivoInterpretacao> arquivos) {
        this.arquivos = arquivos;
    }


    

    
    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }


    
    
}
