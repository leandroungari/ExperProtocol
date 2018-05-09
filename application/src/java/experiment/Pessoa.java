package experiment;

/**
 *
 * @author Joao Pucci Neto
 */

/** Class that defines all data a participant in an experiment */
public class Pessoa extends Element {

    
    private String nome;
    private String email;
    private String telefone;
    

    
    private Instituicao instituicao;

    public Pessoa() {
        super("pessoa");
    }

   


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /*public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getEmailSenha() {
        return emailSenha;
    }

    public void setEmailSenha(String emailSenha) {
        this.emailSenha = emailSenha;
    }*/

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    
    
}
