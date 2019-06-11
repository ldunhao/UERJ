import java.io.*;

public class Ex1{
    public static void main(String args[])throws IOException{
            float[] V = new float[3];   
            if(args.length == 0){
                System.out.println("Eh necessario digitar entre 1 e 3 argumentos!");
            }else if(args.length > 3){
                System.out.println("Foram passados mais argumentos que o necessario!");
            }else{
                boolean ok = false;
                try{
                    if(args.length == 1){
                        V[0] = Float.parseFloat(args[0]);
                    }else if(args.length == 2){
                        V[0] = Float.parseFloat(args[0]);
                        V[1] = Float.parseFloat(args[1]);
                    }else{
                        V[0] = Float.parseFloat(args[0]);
                        V[1] = Float.parseFloat(args[1]);
                        V[2] = Float.parseFloat(args[2]);
                    }
                    ok = true;
                    for(int i=0;i<args.length;i++){
                        if(Float.parseFloat(args[i]) <= 0){
                            System.out.println("Numeros negativos e zero sao numeros invalidos!");
                            ok = false;
                        }
                    }
                }catch(NumberFormatException e){
                    System.out.println("Caracter invalido!");
                }

                if(ok){
                    if(args.length == 1){
                        System.out.printf("Area do Circulo eh: %.2f",Calculos.calcula(V[0]));
                    }else if(args.length == 2){
                        System.out.printf("Area do Retangulo eh: %.2f",Calculos.calcula(V[0],V[1]));
                    }else{
                        if(Calculos.existe(V[0],V[1],V[2])){
                            System.out.printf("Area do Triangulo eh: %.2f",Calculos.calcula(V[0],V[1],V[2]));
                        }
                    }
                }

            }
    }
}