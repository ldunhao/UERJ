
import java.io.*;
import java.lang.Math;
public class Ex2{
    public static double a;
    public static double x;
    public static void main(String args[])throws IOException{
        try{
            Double arg = Double.parseDouble(args[0]);
            Angulo.converterAngulo(arg);
            System.out.printf("Seno : %.2f\n",Angulo.funcaoSeno(arg));
            System.out.printf("Cosseno : %.2f\n",Angulo.funcaoCoseno(arg));
            System.out.printf("Tangente : %.2f\n",Angulo.funcaoTangente(arg));
            System.out.printf("Cotangente : %.2f\n",Angulo.funcaoCotangente(arg));
            while(true){
                System.out.printf("\n");
                System.out.printf("Digite uma medida em graus do angulo:\n");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String aux;
                aux = in.readLine();
                a = Double.parseDouble(aux);
                System.out.printf("Seno : %.2f\n",Angulo.funcaoSeno(a));
                System.out.printf("Cosseno : %.2f\n",Angulo.funcaoCoseno(a));
                System.out.printf("Tangente : %.2f\n",Angulo.funcaoTangente(a));
                System.out.printf("Cotangente : %.2f\n",Angulo.funcaoCotangente(a));
                }
        }catch(NumberFormatException e){
            System.out.println("Valor digitado nao eh um numero!");
            return;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(("Inicie com um valor na linha de comando!"));
            return;
        }
    }
}
