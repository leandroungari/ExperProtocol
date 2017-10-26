package experiment;




public class HipoteseAlternativa extends Element {
    
    
    //private int idhipotese_alternativa;
    //private int hipotese_idhipotese;
    private String alternativa;
    //private String idhipotese;
    private boolean protegido = false;

    public HipoteseAlternativa() {
        super("hipoteseAlternativa");
    }

    public HipoteseAlternativa(String alternativa) {
        super("hipoteseAlternativa");
        this.alternativa = alternativa;
    }

    
    
    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    
    
    
    
}
