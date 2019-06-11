import java.io.*;
import java.lang.Math;

public class Ex3{
    public static boolean isNumeric1(String x){
        try{
            Double.parseDouble(x);
            if(Double.parseDouble(x) < 0){
                System.out.println("Numero invalido!");
                return false;
            }
            return true;
        }catch(NumberFormatException e){
            System.out.println("Nao e numero!");
            return false;
        }
    }
    public static boolean isNumeric2(String x){
        try{
            Double.parseDouble(x);
            return true;
        }catch(NumberFormatException e){
            System.out.println("Nao e numero!");
            return false;
        }
    }
    public static void main(String args[])throws IOException{
            int i;
            int p=3,l=3;
            double x;
            System.out.printf("Numeros de angulos?\n");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String aux;
            aux = in.readLine();
            while(l>0 && !isNumeric1(aux)){
                System.out.println("Voce tem mais " + l + " tentativas");
                System.out.println("Digite a medida novamente");
                aux=in.readLine();
                l--;
            }
            if(!isNumeric1(aux)){return;};
            int a = Integer.parseInt(aux);
            AnguloObj vector[]=new AnguloObj[a];
            for(i=0;i<a;i++){
                System.out.printf("Digite a medida em graus do " + (i+1) + " angulo: ");
                BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
                String aux2;
                aux2 = in2.readLine();
                while(p!=0 && !isNumeric2(aux2)){
                    System.out.println("Voce tem mais " + p + " tentativas");
                    System.out.println("Digite a medida novamente");
                    aux2=in2.readLine();
                    p--;
                }
                if(!isNumeric2(aux2)){return;};
                double b = Double.parseDouble(aux2);
                vector[i] =new AnguloObj(b);
                p=3;
            }
            System.out.printf("\n====== Resultado ======\n\n");
            for(i=0;i<a;i++){
                System.out.println(vector[i].toString());
            }
            System.out.printf("========================\n\n");
    }
}
