package experiment;

import java.util.Calendar;




public class RespostaConfiguracao extends Element {

    //private int idresposta_configuracao;
    private String observacao;
    private String arquivo;
    private Calendar data_inicial;
    private Calendar data_final;
    //private int configuracao_idconfiguracao;
    //private int participante_idparticipante;
    private String justificativa;

    public RespostaConfiguracao() {
        super("respostaConfiguracao");
    }

    public RespostaConfiguracao(String observacao, String arquivo, Calendar data_inicial, Calendar data_final, String justificativa) {
        super("respostaConfiguracao");
        this.observacao = observacao;
        this.arquivo = arquivo;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.justificativa = justificativa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Calendar getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Calendar data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Calendar getData_final() {
        return data_final;
    }

    public void setData_final(Calendar data_final) {
        this.data_final = data_final;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    
    
    
    
}
