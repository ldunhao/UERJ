import java.util.*;

public class MinhaListaOrdenavel{
    ArrayList<PessoaIMC> list = new ArrayList<PessoaIMC>();
    
    public void add(PessoaIMC p){
        list.add(p);
    }  
    
    public  PessoaIMC get(int i){
        return list.get(i);
    }
    
    public Comparator<Object> nomeC = new Comparator<Object>(){
        public int compare(Object p1, Object p2){
            String nome1 , nome2;
            PessoaIMC ob1 = (PessoaIMC) p1;
            PessoaIMC ob2 = (PessoaIMC) p2;
            nome1 = ob1.getNome();
            nome2 = ob2.getNome();
            return (int)Math.round(nome1.compareTo(nome2));
        }
    };

    public Comparator<Object> pesoC = new Comparator<Object>(){
        public int compare(Object p1, Object p2){
            double peso1 , peso2;
            PessoaIMC ob1 = (PessoaIMC) p1;
            PessoaIMC ob2 = (PessoaIMC) p2;
            peso1 = ob1.getPeso();
            peso2 = ob2.getPeso();
            return (int)Math.round(peso2 - peso1);
        }
    };

    public Comparator<Object> alturaC = new Comparator<Object>(){
        public int compare(Object p1, Object p2){
            double altura1 , altura2;
            PessoaIMC ob1 = (PessoaIMC) p1;
            PessoaIMC ob2 = (PessoaIMC) p2;
            altura1 = ob1.getAltura();
            altura2 = ob2.getAltura();
            return (int)Math.floor(altura2 - altura1);
        }
    };

    public Comparator<Object> imcC = new Comparator<Object>(){
        public int compare(Object p1, Object p2){
            double imc1 , imc2;
            PessoaIMC ob1 = (PessoaIMC) p1;
            PessoaIMC ob2 = (PessoaIMC) p2;
            imc1 = ob1.getIMC();
            imc2 = ob2.getIMC();
            return (int)Math.floor(imc2 - imc1);
        }
    };

    public Comparator<Object> generoC = new Comparator<Object>(){
        public int compare(Object p1,Object p2){
            PessoaIMC ob1 = (PessoaIMC) p1;
            PessoaIMC ob2 = (PessoaIMC) p2;
            if((ob1 instanceof Mulher) && (ob2 instanceof Homem)){
                return -1;
            }else if((ob1 instanceof Homem) && (ob2 instanceof Mulher)){
                return 1;
            }else{
                return 0;
            }
        }
    };

    public ArrayList<PessoaIMC> ordena(int escolha){
        switch(escolha){
            case 1: 
                System.out.println("    ORDEM ALFABETICA (A-Z)");
                Collections.sort(list,nomeC);
                break;
            case 2:
                System.out.println("    ORDEM ALFABETICA (Z-A)");
                Collections.sort(list,nomeC.reversed());
                break;
            case 3:
                System.out.println("    MAIOR PESO");
                Collections.sort(list,pesoC);
                break;
            case 4:
                System.out.println("    MENOR PESO");
                Collections.sort(list,pesoC.reversed());
                break;
            case 5:
                System.out.println("    MAIOR ALTURA");
                Collections.sort(list,alturaC);
                break;
            case 6:
                System.out.println("    MENOR ALTURA");
                Collections.sort(list,alturaC.reversed());
                break;
            case 7:
                System.out.println("    MAIOR IMC");
                Collections.sort(list,imcC);
                break;
            case 8:
                System.out.println("    MENOR IMC");
                Collections.sort(list,imcC.reversed());
                break;   
            case 9:
                System.out.println("    GENERO");
                Collections.sort(list,generoC);
                break;    
        }
        return list;
    }
}