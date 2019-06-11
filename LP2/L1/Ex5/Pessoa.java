
class Pessoa{
    private String nome;
    private String dataNascimento;

    public Pessoa(String n, String data){
        this.nome = n;
        this.dataNascimento = data;
    }
    
    public String getNome(){
        return this.nome;
    }
    public String getData(){
        return this.dataNascimento;
    }

    @Override
    public String toString(){
        return "Nome: "+getNome()+"\n"+
               "Data de nascimento: "+getData();
    }
}