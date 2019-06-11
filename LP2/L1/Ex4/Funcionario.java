import java.text.DecimalFormat;

class Funcionario{
    private String nome;
    private String codigo;
    private float salario;
    private float salario_liquido;
    
    public Funcionario(String nom, String cdg, float sal){
        this.nome = nom;
        this.codigo = cdg;
        this.salario = sal;
        this.salario_liquido = sal;
    }

    public double calculaSalario(double desconto){
        return salario_liquido = salario - ((float)desconto*salario);
    }
    
    public String getNome(){
        return this.nome;
    }
    public String getCdg(){
        return this.codigo;
    }
    public float getSal(){
        return this.salario;
    }
    public float getSal_liq(){
        return this.salario_liquido;
    }
    public void setSal(float sal){
        this.salario = sal;
    }
    public void setSal_liq(float salario_liquido){
        this.salario_liquido = salario_liquido;
    }

    @Override
    public String toString(){
        DecimalFormat val = new DecimalFormat();
        val.applyPattern("R$ #0.00");
        return "Nome: " + getNome() + "\n" +
                "Codigo: " + getCdg() + "\n" +
                "Salario: " + val.format(getSal());
    }
}