package experiment;




public class Experimentador extends Element {

   
    private String nome;
    private String email;
    //private String senha;
    //private String senhanova;

    public Experimentador() {
        super("experimentador");
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

    
  
}
