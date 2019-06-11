class Angulo{
    public static double x;

    public static double converterAngulo(Double arg){
        x = Math.toRadians(arg);
        return x;
    }
    public static double funcaoSeno(double x){
        double y,k;
        k = converterAngulo(x);
        y = Math.sin(k);
        return y;
    }
    public static double funcaoCoseno(double x){
        double y,k;
        k = converterAngulo(x);
        y = Math.cos(k);
        return y;
    }
    public static double funcaoTangente(double x){
        double y,k;
        k = converterAngulo(x);
        y = Math.tan(k);
        return y;
    }public static double funcaoCotangente(double x){
        double y,k;
        k = converterAngulo(x);
        y = 1/Math.tan(k);
        return y;
    }
}