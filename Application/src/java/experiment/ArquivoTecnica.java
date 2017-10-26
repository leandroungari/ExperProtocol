package experiment;



public class ArquivoTecnica  extends Element{
    //private int idarquivo_tecnica;
    private String path_arquivo;
    //private int tecnica_idtecnica;
    private boolean protegido = false;

    public ArquivoTecnica(String path_arquivo) {
        super("arquivoTecnica");
        this.path_arquivo = path_arquivo;
    }

    public ArquivoTecnica() {
        super("arquivoTecnica");
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
