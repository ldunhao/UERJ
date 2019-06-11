
abstract class PessoaIMC extends Pessoa{
    private double peso;
    private double altura;
    private double imc;
    public PessoaIMC(String n, String data, Double p, Double h){
        super(n, data);
        this.peso = p;
        this.altura = h;
    }

    public Double getPeso(){
        return this.peso;
    }
    public Double getAltura(){
        return this.altura;
    }

    public Double CalculaIMC(Double p, Double h){
        double x = p/(h*h);
        this.imc = x;
        return x;
    }
    public Double getIMC(){
        return this.imc;
    }

    abstract public String resultIMC();

    @Override
    
    public String toString(){
            return super.toString()+"\n"+
                   "Peso: "+getPeso()+"\n"+
                   "Altura: "+getAltura()+"\n"; 
    }
}