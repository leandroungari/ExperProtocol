package experiment;


public class ArquivoInterpretacao extends Element {

    
    private String path_arquivo;

    public ArquivoInterpretacao(String path_arquivo) {
        super("arquivoInterpretacao");
        this.path_arquivo = path_arquivo;
    }

    
    public ArquivoInterpretacao() {
        super("arquivoInterpretacao");
    }
  
    public String getPath_arquivo() {
        return path_arquivo;
    }

    public void setPath_arquivo(String path_arquivo) {
        this.path_arquivo = path_arquivo;
    }
 
    


}
