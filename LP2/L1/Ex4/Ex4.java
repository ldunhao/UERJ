import java.io.*;
import java.util.ArrayList;


public class Ex4{
    private static String resp;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int numtot=0,i=0;

    public Ex4(int numtot){
        Ex4.numtot = numtot;
    }

    private static void calculaSalarios()throws IOException{
        String nome, codigo;
        float salario;
        int dependentes;
        FuncionarioContratado[] funcs = new FuncionarioContratado[numtot];
        for(i=0;i<numtot;i++){
            System.out.println(" ");
            System.out.printf("Nome do Empregado: ");
            resp = in.readLine();
            while(resp.length() == 0){
                System.out.println("Nome vazio nao e permitido, favor escrever um nome valido! ");
                System.out.printf("Nome do Empregado: ");
                resp = in.readLine();
            }
            nome = resp;
            //--------------------------------------------
            System.out.printf("Codigo: ");
            resp = in.readLine();
            while(resp.length() == 0 ){
                System.out.println("Codigo vazio nao e permitido, favor escrever um codigo valido! ");
                System.out.printf("Codigo: ");
                resp = in.readLine();
            }
            codigo = resp;
            //--------------------------------------------
            System.out.printf("Salario: ");
            resp = in.readLine();
            while(Float.parseFloat(resp) < 0 ){
                System.out.println("Salario negativo nao e permitido, favor escrever um salario valido! ");
                System.out.printf("Salario: ");
                resp = in.readLine();
            }
            salario = Float.parseFloat(resp);
            //--------------------------------------------
            System.out.printf("Numero de dependentes: ");
            resp = in.readLine();
            while(Integer.parseInt(resp) < 0){
                System.out.println("Numero de dependentes negativo nao e permitido, favor escrever um numero valido! ");
                System.out.printf("Numero de dependentes: ");
                resp = in.readLine();
            }
            dependentes = Integer.parseInt(resp);
            System.out.println("------------------");
            //--------------------------------------------
            funcs[i] = new FuncionarioContratado(nome,codigo,salario,dependentes);
            funcs[i].getCalculaSalario(dependentes);
        }
        System.out.println("------- Folha Salarial");
        System.out.println(" ");
        for(i=0;i<numtot;i++){
            System.out.println(funcs[i].toString());
            System.out.println("--------------------");
        }
        
        
    }
    
    public static void main(String args[])throws IOException{
        try {
            System.out.println("------- Cadastro de Funcionarios");
            System.out.println("Digite o numero de funcionarios que serao cadastrados!");
            System.out.printf("Numero: ");
            resp = in.readLine();
            while(Integer.parseInt(resp)<0){
                System.out.println("Numero negativo nao e permitido, favor escrever um numero valido!");
                System.out.printf("Numero: ");
                resp = in.readLine();
            }
            numtot = Integer.parseInt(resp);
        }catch(NumberFormatException e){
            System.out.println("--Erro");
        }
        Ex4 Ex4 = new Ex4(numtot);
        Ex4.calculaSalarios();
    }
}