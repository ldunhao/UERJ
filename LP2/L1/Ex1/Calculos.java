import java.lang.Math;
public class Calculos {
    public static float a;
    public static float calcula(float r){
        double aux=Math.PI;
        float pi = (float)aux;
        a = pi*r*r;
        return a;
    }
    public static float calcula(float l1, float l2){
        a = l1*l2;
        return a;
    }
    public static float calcula(float t1,float t2,float t3){
        float p,sqrt;
        p = (t1+t2+t3)/2;
        double aux=Math.sqrt(p*(p-t1)*(p-t2)*(p-t3));
        sqrt = (float)aux;
        return sqrt;
    }
    public static void tipo(float t1,float t2,float t3){
        if(t1==t2 && t2==t3){
            System.out.println("O triangulo e Equilatero!");
        }else if(t1==t2 || t2==t3 || t1==t3){
            System.out.println("O triangulo e Isoceles!");
        }else{
            System.out.println("O triangulo e Escaleno!");
        }
    }
    public static boolean existe(float t1,float t2,float t3){
        boolean T=true;
        if(t1>t2 && t1>t3){
            if(t2+t3<t1){
                System.out.println("Condicao de existencia do triangulo invalida");
                T = false;
            }
        }else if(t2>t3){
            if(t1+t3<t2){
                System.out.println("Condicao de existencia do triangulo invalida");
                T = false;
            }
        }else{
            if(t1+t2<t3){
                System.out.println("Condicao de existencia do triangulo invalida");
                T = false;
            }
        }
        if(T){
            tipo(t1, t2, t3);
        }
        return T;
    }
}