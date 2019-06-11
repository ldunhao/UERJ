import java.text.DecimalFormat;

class FuncionarioContratado extends Funcionario{
    private int numDependentes;
    private float salario_familia;
    final double valorPorDep = 9.58;
    final double  aliquotaIR = 0.15;

    public FuncionarioContratado(String nom, String cdg, float sal , int numDep){
        super(nom, cdg, sal);
        this.numDependentes = numDep;
    }

    public double calculaSalario(){
        return super.calculaSalario(aliquotaIR);
    }

    private void calculaSalario(int numDependentes){
        this.salario_familia = numDependentes* (float)valorPorDep;
        setSal(getSal() + salario_familia);
        calculaSalario();
    }
    public void getCalculaSalario(int numDependentes){
        calculaSalario(numDependentes);
    }

    @Override
    public String toString(){
        DecimalFormat val = new DecimalFormat();
        val.applyPattern("R$ #0.00");
        return super.toString()+"\n"+
                "Salario-liquido: "+val.format(getSal_liq())+"\n";
    }
}