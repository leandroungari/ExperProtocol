package experiment;

/**
 *
 * @author Joao Pucci Neto
 */

/** Class that defines all data a participant in an experiment */
public class Pessoa extends Element {

    private int idpessoa;
    private String nome;
    private String email;
    private String telefone;
    
    //private String senha;
    //private String emailLogin;
    //private String emailSenha; 
    
    private Instituicao instituicao;

    public Pessoa() {
        super("pessoa");
    }

    public Pessoa(int idpessoa, String nome, String email, String telefone/*, String senha, String emailLogin, String emailSenha, */, Instituicao instituicao) {
        super("pessoa");
        this.idpessoa = idpessoa;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        //this.senha = senha;
        //this.emailLogin = emailLogin;
        //this.emailSenha = emailSenha;
        this.instituicao = instituicao;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
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
