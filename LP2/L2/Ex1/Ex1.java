import java.io.*;
import java.util.ArrayList;

public class Ex1{
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String args[])throws IOException{
        MinhaListaOrdenavel pessoas = new MinhaListaOrdenavel();

        pessoas.add(new Homem("Lucas","27/08/1999",84.00,1.86));
        pessoas.add(new Homem("Pedro","04/12/1960",94.00,1.84));
        pessoas.add(new Homem("Pedro Henrique","10/10/1991",80.00,1.84));
        pessoas.add(new Mulher("Maria","11/12/1958",74.00,1.75));
        pessoas.add(new Mulher("Laura","12/07/1998",64.00,1.65));
        pessoas.add(new Mulher("Sophie","17/05/1999",68.00,1.80));
        pessoas.add(new Homem("Felipe","28/04/1999",98.00,2.05));
        pessoas.add(new Homem("Paulo","18/07/1957",70.00,1.67));
        pessoas.add(new Mulher("Talita","22/11/1985",72.00,1.81));
        pessoas.add(new Mulher("Mariana","27/08/1982",68.00,1.74));
        
        int escolha1, escolha2;
        boolean ok = false;

        System.out.println("1. Imprimir Lista");
        System.out.println("2. Sair");
        System.out.printf("Escolha a sua opcao: ");
        escolha1 = Integer.parseInt(in.readLine());
        System.out.println(" ");
        

        if(escolha1==2 || escolha1==1){
            ok = true;
        }else{
            System.out.println("Opcao invalida, escolher as opcoes 1 ou 2!");
            System.out.println("1. Imprimir Lista");
            System.out.println("2. Sair");
            System.out.printf("Escolha a sua opcao: ");
            escolha1 = Integer.parseInt(in.readLine());
            System.out.println(" ");
            ok = true;
        }

        while(ok){
            if(escolha1==2 || escolha1==1){
                ok = true;
            }else{
                System.out.println("Opcao invalida, escolher as opcoes 1 ou 2!");
                System.out.println("1. Imprimir Lista");
                System.out.println("2. Sair");
                System.out.printf("Escolha a sua opcao: ");
                escolha1 = Integer.parseInt(in.readLine());
                System.out.println(" ");
                ok = true;
            }
            switch(escolha1){
                case 1:
                    System.out.println("1. Ordem Alfabetica (A-Z)");
                    System.out.println("2. Ordem Alfabetica (Z-A)");
                    System.out.println("3. Maior Peso");
                    System.out.println("4. Menor Peso");
                    System.out.println("5. Maior Altura");
                    System.out.println("6. Menor Altura");
                    System.out.println("7. Maior IMC");
                    System.out.println("8. Menor IMC");
                    System.out.println("9. Genero");
                    System.out.printf("Escolha a sua opcao: ");
                    escolha2 = Integer.parseInt(in.readLine());
                    System.out.println(" ");
                    if(escolha2<1 || escolha2>9){
                        System.out.println(" ");
                        System.out.println("Opcao invalida!");
                        System.out.println(" ");
                        break;
                    }
                    System.out.println(" ");
                    pessoas.ordena(escolha2);
                    System.out.println(" --------------- ");
                    for(int i=0;i<10;i++){
                        System.out.println(pessoas.get(i));
                    }
                    System.out.println(" --------------- ");
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Programa finalizado!");
                    break;
            }
            if(escolha1==2){break;}
            System.out.println("1. Imprimir Lista");
            System.out.println("2. Sair");
            System.out.printf("Escolha a sua opcao: ");
            escolha1 = Integer.parseInt(in.readLine());
            System.out.println(" ");
        }
    }
}