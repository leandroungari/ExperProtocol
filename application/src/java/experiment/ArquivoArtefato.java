package experiment;


public class ArquivoArtefato  extends Element{
    
    
    private String path_arquivo;
  

    public ArquivoArtefato() {
        super("arquivoArtefato");
    }

    public ArquivoArtefato(String path_arquivo) {
        super("arquivoArtefato");
        this.path_arquivo = path_arquivo;
    }

    public String getPath_arquivo() {
        return path_arquivo;
    }

    public void setPath_arquivo(String path_arquivo) {
        this.path_arquivo = path_arquivo;
    }

}
