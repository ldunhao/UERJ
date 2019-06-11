
class Mulher extends PessoaIMC{
    public String imc;
    public String result;
    double conta = CalculaIMC(getPeso(), getAltura());
    public Mulher(String n, String data, Double p, Double h){
        super(n, data, p, h);
    }
    public String resultIMC(){
        
        if(conta<19){
            imc = "Abaixo do peso ideal\n";
        }else if(conta<25.8){
            imc = "Peso ideal\n";
        }else{
            imc = "Acima do peso ideal\n";
        }
        return imc;
    }
    @Override
    public String toString(){
        result = String.format("IMC: %.2f",conta);
        return super.toString()+
                result+" ==> "+resultIMC();
    }
}