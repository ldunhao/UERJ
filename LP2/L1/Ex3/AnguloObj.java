class AnguloObj{
    private double arcoRad;
    public AnguloObj(double x){
        this.arcoRad=Math.toRadians(x); 
    }
    public double getarcoRad(){
        return this.arcoRad;
    }
    public double funcaoSeno(){
        return Math.sin(this.arcoRad);
    }
    public double funcaoCoseno(){
        return Math.cos(this.arcoRad);
    }
    public double funcaoTangente(){
        return Math.tan(this.arcoRad);
    }public double funcaoCotangente(){
        return 1/Math.tan(this.arcoRad);
    }
    @Override
    public String toString(){
        String a,b,c,d,e;
        a = String.format("Arco: %.2f\n",this.arcoRad);
        b = String.format("Seno: %.2f\n",funcaoSeno());
        c = String.format("Cosseno: %.2f\n",funcaoCoseno());
        d = String.format("Tangente: %.2f\n",funcaoTangente());
        e = String.format("Cotangente: %.2f\n",funcaoCotangente());
        return a+b+c+d+e;
    }
}