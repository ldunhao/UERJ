public interface CalcIntf{
    public int soma(int a, int b)throws Erro;
    public int sub(int a, int b)throws Erro;
    public double mult(double a, double b);
    public double div( double a , double b)throws DivZero;
}