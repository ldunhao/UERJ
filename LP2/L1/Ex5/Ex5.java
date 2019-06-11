import java.io.*;
import java.util.ArrayList;

public class Ex5{
    private static String resp;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[])throws IOException{
        int i,numtot=0;
        String nome,data;
        char sexo;
        Double peso,altura;
        boolean verificado;
        System.out.println("--------------------");
        System.out.printf("Numero de pessoas: ");
        resp = in.readLine();
        while(Integer.parseInt(resp)<0){
            System.out.println("Numero negativo nao e permitido, favor escrever um numero valido!");
            System.out.printf("Numero de pessoas: ");
            resp = in.readLine();
        }
        numtot = Integer.parseInt(resp);
        ArrayList list = new ArrayList<>();

        for(i=0;i<numtot;i++){
            System.out.println("Digite h para homem ou m para mulher!!");
            System.out.printf("Qual o sexo da pessoa? ");
            resp = in.readLine();
            while(resp.charAt(0)!='h' && resp.charAt(0)!='m' && resp.length()!=0){
                System.out.println(" ");
                System.out.println("----- Opcao invalida!! -----");
                System.out.println(" ");
                System.out.println(" Digite h para homem ou m para mulher!!");
                System.out.printf("Qual o sexo da pessoa? ");
                resp = in.readLine();
            }
            sexo = resp.charAt(0);
            //=========================================
            System.out.println("Digite o nome da pessoa:");
            System.out.printf("Nome: ");
            resp = in.readLine();
            while(resp.length() == 0){
                System.out.println("Nome vazio nao e permitido, favor escrever um nome valido! ");
                System.out.println("Digite o nome da pessoa:");
                System.out.printf("Nome: ");
                resp = in.readLine();
            }
            nome = resp;
            //=========================================
            System.out.println("Digite a data de nascimento!");
            System.out.printf("Data de Nascimento: ");
            resp = in.readLine();
            while(resp.length() == 0){
                System.out.println("Data vazia nao e permitida, favor escrever uma data valida! ");
                System.out.println("Digite a data de nascimento!");
                System.out.printf("Data de Nascimento: ");
                resp = in.readLine();
            }
            data = resp;
            //=========================================
            verificado = false;
            while(!verificado){
                try{
                    System.out.println("Digite o seu peso!");
                    System.out.printf("Peso: ");
                    resp = in.readLine();
                    verificado = true;
                }catch(NumberFormatException e){
                    System.out.println("--- Digite um peso valido!");
                    verificado = false;
                }
            }
            peso = Double.parseDouble(resp);
            //=========================================
            verificado = false;
            while(!verificado){
                try{
                    System.out.println("Digite o sua altura (em metros)!");
                    System.out.printf("Altura: ");
                    resp = in.readLine();
                    verificado = true;
                }catch(NumberFormatException e){
                    System.out.println("--- Digite uma altura valida!");
                    verificado = false;
                }
            }
            altura = Double.parseDouble(resp);

            if(sexo == 'h'){
                list.add(new Homem(nome,data,peso,altura));
            }else if(sexo == 'm'){
                list.add(new Mulher(nome,data,peso,altura));
            }
        }
        System.out.println(" ");
        System.out.println("--------------------");
        for(i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}