import lp2g22.biblioteca.*;
import lp2g22.exceptions.*;
import java.io.*;

public class Ex2{
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Biblioteca biblioteca = new Biblioteca();
    
    public final static void limpar(){
        try{
            final String os = System.getProperty("os.name");
            if(os.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                Runtime.getRuntime().exec("clear");
            }
        }catch (final InterruptedException | IOException e){
            System.out.println(e);
        }
    }

    public static boolean valido(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }  
    }

    public static boolean UsuJaCad(int CdgUsu){
        try{
            biblioteca.getUsuario(CdgUsu);
            return true;
        }catch(UsuarioNaoCadastradoEx e){
            return false;
        }
    }
    
    public static boolean LivJaCad(String codigoLivro){
        try{
            biblioteca.getLivro(codigoLivro);
            return true;
        }catch(LivroNaoCadastradoEx e){
            return false;
        }
    }
    
    public static void Manutencao(String arq1, String arq2){
        try{
            FileInputStream inputStream1 = new FileInputStream(arq1);
            FileInputStream inputStream2 = new FileInputStream(arq2);
            biblioteca = new Biblioteca(arq1, arq2);
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    public static void Cadastro() throws IOException{
        String strOpcao, nome, endereco, strDia, strMes, strAno, strcdgUsu;
        String strcdgLiv, tit, cat, strqntdLiv, strlivEmpr;
        int diaNasc, mesNasc, anoNasc,livEmpr, cdgUsu, qntdLiv, opcaoArq, opcao;
        String strOpcaoArq, ArqUsu, ArqLiv;

        boolean temUsu, temLiv;
        temUsu = temLiv = true;

        do{
            limpar();
            System.out.println("------> CADASTRO <------");
            System.out.println("1 - Cadastro de Usuarios");
            System.out.println("2 - Cadastro de Livros");
            System.out.println("3 - Salvar em Arquivo");
            System.out.println("4 - Sair");
            System.out.printf("Escolha a sua opcao: ");
            strOpcao = in.readLine();
            
            while(!valido(strOpcao)){
                System.out.println("O numero digitado nao e valido, digite outra vez!");
                strOpcao = in.readLine();
            }
            opcao = Integer.parseInt(strOpcao);
            while(opcao<=0 || opcao>=5){
                limpar();
                System.out.println("Selecione uma opcao valida!");
                System.out.println("------> CADASTRO <------");
                System.out.println("1 - Cadastro de Usuarios");
                System.out.println("2 - Cadastro de Livros");
                System.out.println("3 - Salvar em Arquivo");
                System.out.println("4 - Sair");
                System.out.printf("Escolha a sua opcao: ");
                strOpcao = in.readLine();
                opcao = Integer.parseInt(strOpcao);
            }
            

            switch(opcao){
                case 1: limpar();
                        System.out.println("------> CADASTRO DE USUARIOS <------");

                        do{
                            System.out.printf("Digite o codigo do Usuario: ");
                            strcdgUsu = in.readLine();
                            while(!valido(strcdgUsu)){
                                System.out.println("O numero digitado nao e valido, digite outra vez!");
                                System.out.printf("Digite o codigo do Usuario: ");
                                strcdgUsu = in.readLine();
                            }
                            cdgUsu = Integer.parseInt(strcdgUsu);
                            if(!UsuJaCad(cdgUsu)){
                                temUsu = false;
                            }

                            while(UsuJaCad(cdgUsu)){
                                System.out.println("Este usuario ja foi cadastrado, digite um outro codigo!");
                                System.out.printf("Digite o codigo do Usuario: ");
                                strcdgUsu = in.readLine();
                                cdgUsu = Integer.parseInt(strcdgUsu);
                            }
                            
                        }while(temUsu);

                        System.out.printf("Nome: ");
                        nome = in.readLine();
                        System.out.println("--> Data de Nascimento <--");
                        do{
                            
                            System.out.printf("Dia do Nascimento: ");
                            strDia = in.readLine();
                            while(!valido(strDia)){
                                System.out.println("O numero digitado nao e valido, digite outra vez!");
                                System.out.printf("Dia do Nascimento: ");
                                strDia = in.readLine();
                            }
                            
                            diaNasc = Integer.parseInt(strDia);
                            if(diaNasc <=0 || diaNasc >= 32){
                                System.out.println("Digite um dia valido!");
                            }
                        }while(diaNasc <=0 || diaNasc >= 32);

                        do{
                            System.out.printf("Mes do Nascimento: ");
                            strMes = in.readLine();
                            while(!valido(strMes)){
                                System.out.println("O numero digitado nao e valido, digite outra vez!");
                                System.out.printf("Mes do Nascimento: ");
                                strMes = in.readLine();
                            }
                            mesNasc = Integer.parseInt(strMes);
                            if(mesNasc <=0 || mesNasc >= 13){
                                System.out.println("Digite um mes valido!");
                            }
                        }while(mesNasc <=0 || mesNasc >= 13);

                        do{
                            System.out.printf("Ano do Nascimento: ");
                            strAno = in.readLine();
                            while(!valido(strAno)){
                                System.out.println("O numero digitado nao e valido, digite outra vez!");
                                System.out.printf("Ano do Nascimento: ");
                                strAno = in.readLine();
                            }
                            anoNasc = Integer.parseInt(strAno);
                            if(anoNasc <=1900 || anoNasc >= 2019){
                                System.out.println("Digite um ano valido!");
                            }
                        }while(anoNasc <=1900 || anoNasc >= 2020);
                        
                        System.out.printf("Endereco: ");
                        endereco = in.readLine();
                        
                        Usuario usuario = new Usuario(nome, diaNasc, mesNasc, anoNasc, endereco, cdgUsu);
                        biblioteca.cadastraUsuario(usuario);
                        System.out.println("Cadastro Realizado!");
                        break;
                
                case 2: limpar();
                        System.out.println("------> CADASTRO DE LIVROS <------");
                        
                        do{
                            System.out.printf("Digite o codigo do Livro: ");
                            strcdgLiv = in.readLine();
                            if(!LivJaCad(strcdgLiv)){
                                temLiv = false;
                            }
                            while(LivJaCad(strcdgLiv)){
                                System.out.println("Este livro ja foi cadastrado, digite o codigo novamente");
                                System.out.printf("Digite o codigo do Livro: ");
                                strcdgLiv = in.readLine();
                            } 
                        }while(temLiv);
                        
                        System.out.printf("Titulo: ");
                        tit = in.readLine();
                        System.out.printf("Categoria: ");
                        cat = in.readLine();

                        do{
                            System.out.printf("Numeros de copias: ");
                            strqntdLiv = in.readLine();
                            while(!valido(strqntdLiv)){
                                System.out.println("O numero digitado nao e valido, digite outra vez!");
                                System.out.printf("Numeros de copias: ");
                                strqntdLiv = in.readLine();
                            }
                            qntdLiv = Integer.parseInt(strqntdLiv);
                            if(qntdLiv <=0){
                                System.out.println("Precisa ter no minimo uma copia!");
                            }
                        }while(qntdLiv <=0);

                        do{
                            
                            System.out.printf("Digite o numero de livros para emprestar: ");
                            strlivEmpr = in.readLine();
                            while(!valido(strlivEmpr)){
                                System.out.println("O numero digitado nao e valido, digite outra vez!");
                                System.out.printf("Digite o numero de livros para emprestar: ");
                                strlivEmpr = in.readLine();
                            }
                            
                            livEmpr = Integer.parseInt(strlivEmpr);
                            if(livEmpr < 0 || livEmpr > qntdLiv){
                                System.out.println("Numero invalido!");
                                System.out.println("Digite um numero entre 0 e " + qntdLiv + " !");
                            }
                        }while(livEmpr < 0 || livEmpr >= qntdLiv);

                        Livro livro = new Livro(strcdgLiv, tit, cat, qntdLiv, livEmpr);
                        biblioteca.cadastraLivro(livro);
                        System.out.println("Cadastro Realizado!");
                        break;
                
                
                case 3: limpar();

                        System.out.println("------> SALVAR EM ARQUIVO <------");
                        do{
                            
                            System.out.println("1 - Salvar o cadastro de Usuario");
                            System.out.println("2 - Salvar o cadastro de Livros");
                            System.out.println("3 - Sair");
                            System.out.printf("Escolha a sua opcao: ");
                            strOpcaoArq = in.readLine();
                            while(!valido(strOpcaoArq)){
                                System.out.println("O numero digitado nao e valido, digite outra vez!");
                                System.out.println("1 - Salvar o cadastro de Usuario");
                                System.out.println("2 - Salvar o cadastro de Livros");
                                System.out.println("3 - Sair");
                                System.out.printf("Escolha a sua opcao: ");
                                strOpcaoArq = in.readLine();
                            }
                            opcaoArq = Integer.parseInt(strOpcaoArq);
                            if(opcaoArq <=0 || opcaoArq >=4){
                                System.out.println("Selecione uma opcao valida!");
                            }
                        }while(opcaoArq <=0 || opcaoArq >=4);

                        switch(opcaoArq){
                            case 1:
                                limpar();
                                System.out.printf("Digite o nome do arquivo: ");
                                ArqUsu = in.readLine();
                                biblioteca.salvaArquivo(biblioteca.getUsuarioHashtable(), ArqUsu);
                                System.out.println("Cadastro salvo com Sucesso!!");
                                break;
                                
                            case 2:
                                limpar();
                                System.out.print("Digite o nome do arquivo de livros: ");
                                ArqLiv = in.readLine();
                                biblioteca.salvaArquivo(biblioteca.getLivroHashtable(), ArqLiv);
                                System.out.println("Cadastro salvo com Sucesso!!");
                                break;
                                
                            case 3: break; 
                        }

                case 4: break;
            }
        }while(opcao!=4);
        
    }

    public static void Emprestimo()throws IOException{
        String strOpcao,sair;
        int opcao;
        

        do{
            do{
                
                limpar();
                System.out.println("------> EMPRESTIMOS <------");
                System.out.println("1 - Imprimir o cadastro de livros.");
                System.out.println("2 - Fazer um emprestimo.");
                System.out.println("3 - Fazer uma devolucao.");
                System.out.println("4 - Voltar.");
                System.out.print("Escolha sua opcao: ");
                strOpcao = in.readLine();

                while(!valido(strOpcao)){
                    System.out.println("O numero digitado nao e valido, digite outra vez!");
                    strOpcao = in.readLine();
                }
                
                opcao = Integer.parseInt(strOpcao);

                if(opcao <= 0 || opcao >= 5){
                    limpar();
                    System.out.println("Selecione uma opcao valida!");
                    System.out.println("------> EMPRESTIMOS <------");
                    System.out.println("1 - Imprimir o cadastro de livros.");
                    System.out.println("2 - Fazer um emprestimo.");
                    System.out.println("3 - Fazer uma devolucao.");
                    System.out.println("4 - Voltar.");
                    System.out.print("Escolha sua opcao: ");
                    strOpcao = in.readLine();
                }
            }while(opcao <= 0 || opcao >= 5);

            switch(opcao){
                case 1:
                    limpar();
                    System.out.println("Cadastro de livros");
                    System.out.println(biblioteca.imprimeLivros());
                    System.out.println("Aperte uma tecla para sair");
                    sair = in.readLine();
                    break;

                
                case 2:
                    limpar();
                    String strCdgUsu, strCdgLiv;
                    int CdgUsu=-1;
                    boolean temLiv, temUsu, bo = false;
                    Usuario usu = null;
                    Livro liv = null;
                    temLiv=temUsu=false;

                    System.out.println(" ===> Fazer Emprestimo");
                    do{
                        System.out.println("Digite o codigo do usuario: ");
                        strCdgUsu = in.readLine();
                        if(strCdgUsu.compareTo("sair")==0){
                            bo = true;
                            break;
                        }
                        if(bo){
                            break;
                        }
                        while(!valido(strCdgUsu)){
                            System.out.println("O numero digitado nao e valido, digite outra vez!");
                            strCdgUsu = in.readLine();
                        }
                        CdgUsu = Integer.parseInt(strCdgUsu);
                        if(UsuJaCad(CdgUsu)){
                            temUsu = true;
                        }else{
                            System.out.println("Nao existe um usuario com esse codigo!");
                            System.out.println("Digite outra vez ou 'sair'!");
                        }
                    }while(!UsuJaCad(CdgUsu));
                    if(bo){
                        break;
                    }
                    do{
                        System.out.println("Digite o codigo do livro: ");
                        strCdgLiv = in.readLine();
                        if(strCdgLiv.compareTo("sair")==0){
                            bo = true;
                            break;
                        }
                        if(bo){
                            break;
                        }
                        if(LivJaCad(strCdgLiv)){
                            temLiv = true;
                        }else{
                            System.out.println("Nao existe um livro com esse codigo!");
                            System.out.println("Digite outra vez ou 'sair'!");
                        }
                    }while(!LivJaCad(strCdgLiv));

                    try{
                        usu = (Usuario) biblioteca.getUsuario(CdgUsu);
                    }catch(UsuarioNaoCadastradoEx e){
                        System.out.println(e);
                    }

                    try{
                        liv = (Livro) biblioteca.getLivro(strCdgLiv);
                    }catch(LivroNaoCadastradoEx e){
                        System.out.println(e);
                    }
                    try{
                        biblioteca.emprestaLivro(usu, liv);
                        System.out.println("--> Emprestimo realizado!");
                    }catch(CopiaNaoDisponivelEx e){
                        System.out.println(e);
                    }
                    System.out.println("Aperte uma tecla para sair");
                    sair = in.readLine();
                    break;
                
                case 3: 
                    limpar();
                    String strCdgUsu2, strCdgLiv2;
                    int CdgUsu2=-1;
                    boolean temLiv2, temUsu2,b=false;
                    Usuario usu2 = null;
                    Livro liv2 = null;
                    temLiv2=temUsu2=false;

                    System.out.println(" ===> Fazer Devolucao");
                    do{
                        
                        System.out.println("Digite o codigo do usuario: ");
                        strCdgUsu2 = in.readLine();
                        if(strCdgUsu2.compareTo("sair")==0){
                            b = true;
                            break;
                        }
                        if(b){
                            break;
                        }
                        while(!valido(strCdgUsu2)){
                            System.out.println("O numero digitado nao e valido, digite outra vez!");
                            strCdgUsu2 = in.readLine();
                        }
                        CdgUsu2 = Integer.parseInt(strCdgUsu2);
                        if(UsuJaCad(CdgUsu2)){
                            temUsu2 = true;
                        }else{
                            System.out.println("Nao existe um usuario com esse codigo!");
                            System.out.println("Digite outra vez ou 'sair'!");
                        }
                    }while(!UsuJaCad(CdgUsu2));

                    if(b){
                        break;
                    }
                    b = false;
                    do{
                        System.out.println("Digite o codigo do livro: ");
                        strCdgLiv2 = in.readLine();
                        if(strCdgLiv2.compareTo("sair")==0){
                            b = true;
                            break;
                        }
                        if(b){
                            break;
                        }
                        if(LivJaCad(strCdgLiv2)){
                            temLiv2 = true;
                        }else{
                            System.out.println("Nao existe um livro com esse codigo!");
                            System.out.println("Digite outra vez ou 'sair'!");
                        }
                    }while(!LivJaCad(strCdgLiv2));

                    try{
                        usu2 = (Usuario) biblioteca.getUsuario(CdgUsu2);
                    }catch(UsuarioNaoCadastradoEx e){
                        System.out.println(e);
                    }

                    try{
                        liv2 = (Livro) biblioteca.getLivro(strCdgLiv2);
                    }catch(LivroNaoCadastradoEx e){
                        System.out.println(e);
                    }

                    try{
                        biblioteca.devolveLivro(usu2, liv2);
                        System.out.println("--> Devolucao realizada!");
                    }catch(NenhumaCopiaEmprestadaEx e){
                        System.out.println(e);
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Nenhuma copia foi emprestada!!");
                    }
                    System.out.println("Aperte uma tecla para sair");
                    sair = in.readLine();
                    break;
                
                case 4: break; 
            }
        }while(opcao!=4);
    }

    public static void Relatorio()throws IOException{
        String strOpcao, strCdgUsu, CdgLiv, sair;
        int opcao, cdgUsu=-1;
        boolean okUsu, okLiv;
        okLiv=okUsu=false;

        do{
            limpar();
            System.out.println("------> RELATORIO <------");
            System.out.println("1 - Lista de livros");
            System.out.println("2 - Lista de usuario");
            System.out.println("3 - Detalhes de um usuario especifico");
            System.out.println("4 - Detalhes de um livro especifico");
            System.out.println("5 - Sair");
            System.out.printf("Escolha a sua opcao: ");
            strOpcao = in.readLine();
            while(!valido(strOpcao)){
                System.out.println("O numero digitado nao e valido, digite outra vez!");
                System.out.println("1 - Lista de livros");
                System.out.println("2 - Lista de usuario");
                System.out.println("3 - Detalhes de um usuario especifico");
                System.out.println("4 - Detalhes de um livro especifico");
                System.out.println("5 - Sair");
                System.out.printf("Escolha a sua opcao: ");
                strOpcao = in.readLine();
            }
            opcao = Integer.parseInt(strOpcao);

            switch(opcao){
                case 1:
                    limpar();
                    System.out.println("------> NOSSO ACERVO <------");
                    System.out.println(biblioteca.getLivroHashtable());
                    System.out.println("Aperte uma tecla para sair");
                    sair = in.readLine();
                    break;
                
                case 2: 
                    limpar();
                    System.out.println("------> NOSSOS USUARIOS <------");
                    System.out.println(biblioteca.getUsuarioHashtable());
                    System.out.println("Aperte uma tecla para sair");
                    sair = in.readLine();
                    break;
                
                case 3:
                    limpar();
                    boolean b = false;
                    System.out.println("------> DETALHES DO USUARIO <------");
                    do{
                        
                        System.out.println("Digite o codigo do usuario: ");
                        strCdgUsu = in.readLine();
                        if(strCdgUsu.compareTo("sair")==0){
                            b = true;
                            break;
                        }
                        if(b){
                            break;
                        }
                        while(!valido(strCdgUsu)){
                            System.out.println("O numero digitado nao e valido, digite outra vez!");
                            strCdgUsu = in.readLine();
                        }
                        
                        cdgUsu = Integer.parseInt(strCdgUsu);
                        if(UsuJaCad(cdgUsu)){
                            okUsu = true;
                        }else{
                            System.out.println("Nao existe um usuario com esse codigo!");
                            System.out.println("Digite outra vez ou 'sair'!");
                        }
                        
                    }while(!UsuJaCad(cdgUsu));
                    
                    try{
                        Usuario usu = (Usuario) biblioteca.getUsuario(cdgUsu);
                        System.out.println(usu.toString());
                    }catch(UsuarioNaoCadastradoEx e){
                        System.out.println(e);
                    }
                    System.out.println("Aperte uma tecla para sair");
                    sair = in.readLine();
                    break;

                case 4:
                    limpar();
                    String sa = "sair";
                    boolean bo = false;
                    System.out.println("------> DETALHES DO LIVRO <------");
                    do{
                        
                        System.out.println("Digite o codigo do livro: ");
                        CdgLiv = in.readLine();
                        if(CdgLiv.compareTo(sa)==0){
                            bo = true;
                            break;
                        }
                        if(bo){
                            break;
                        }
                        if(LivJaCad(CdgLiv)){
                            okLiv = true;
                        }else{
                            System.out.println("Nao existe um livro com esse codigo!");
                            System.out.println("Digite outra vez ou 'sair'!");
                        }
                    }while(!LivJaCad(CdgLiv));
                    
                    try{
                        Livro liv = (Livro) biblioteca.getLivro(CdgLiv);
                        System.out.println(liv.toString());
                    }catch(LivroNaoCadastradoEx e){
                        System.out.println(e);
                    }
                    System.out.println("Aperte uma tecla para sair");
                    sair = in.readLine();
                    break;

                case 5:
                    break;
            }
        }while(opcao!=5);
    }


    public static void main(String args[])throws IOException{
        int opcao;
        String strOpcao, ArqUsu, ArqLiv;

        do{
            limpar();
            System.out.println("------> BIBLIOTECA <------");
            System.out.println("1 - MANUTENCAO");
            System.out.println("2 - CADASTRO");
            System.out.println("3 - EMPRESTIMO");
            System.out.println("4 - RELATORIO");
            System.out.println("5 - SAIR");
            System.out.printf("Escolha a sua opcao: ");
            strOpcao = in.readLine();
            while(!valido(strOpcao)){
                System.out.println("O numero digitado nao e valido, digite outra vez!");
                System.out.println("1 - MANUTENCAO");
                System.out.println("2 - CADASTRO");
                System.out.println("3 - EMPRESTIMO");
                System.out.println("4 - RELATORIO");
                System.out.println("5 - SAIR");
                System.out.printf("Escolha a sua opcao: ");
                strOpcao = in.readLine();
            }

            opcao= Integer.parseInt(strOpcao);
            
            if(opcao <= 0 || opcao >= 6){
                System.out.println("Selecione uma opcao valida!");
            }

            switch(opcao){
                case 1: 
                    limpar();
                    System.out.println("------> MANUTENCAO <------");
                    System.out.printf("Nome do arquivo de usuarios: ");
                    ArqUsu = in.readLine();
                    System.out.printf("Nome do arquivo de livros: ");
                    ArqLiv = in.readLine();
                    Manutencao(ArqUsu, ArqLiv);
                    break;
                
                case 2:
                    limpar();
                    Cadastro();
                    break;
                
                case 3:
                    limpar();
                    Emprestimo();
                    break;
                
                case 4:
                    limpar();
                    Relatorio();
                    break;
                    
                case 5:
                    limpar();
                    System.out.println("------> PROGRAMA FINALIZADO <------");
                    break;
            }


        }while(opcao != 5);
    
        

    }
}