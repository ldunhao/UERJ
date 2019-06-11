public class Calc implements CalcIntf{
    public int soma(int c, int d)throws Erro{
        if(c > 100 || d > 100){
            throw new Erro();
        }else{
            return c + d;
        }
    }
    public int sub(int c, int d)throws Erro{
        if(d>c){
            throw new Erro();
        }else{
            return c - d;
        }
    }
    public double mult(double a, double b){
        return a * b;
    }
    public double div( double a , double b)throws DivZero{
        if(b==0){
            throw new DivZero();
        }else{
            return a / b;
        }     
    }
}