package experiment;


public class ArquivoArtefato  extends Element{
    
    //private int idarquivo_artefato;
    private String path_arquivo;
    //private int artefato_idartefato;
    private boolean protegido = false;

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

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }
}
