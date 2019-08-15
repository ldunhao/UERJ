package lp2g22.biblioteca;

import java.io.*;
import java.util.*;
import lp2g22.exceptions.*;

public class Biblioteca implements Serializable{
    private Hashtable usuarios;
    private Hashtable livros;

    public Biblioteca(){
        usuarios = new Hashtable();
        livros = new Hashtable();
    }

    public Biblioteca(String ArqUsuario, String ArqLivros){
        try{
            ObjectInputStream inputStream1 = new ObjectInputStream(new FileInputStream(ArqUsuario));
            ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream(ArqLivros));
            usuarios = (Hashtable)inputStream1.readObject();
            livros = (Hashtable)inputStream2.readObject();
            inputStream1.close();
            inputStream2.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public void cadastraUsuario(Usuario usuario){
        usuarios.put(usuario.codigoUsuario,usuario);
    }
    public void cadastraLivro(Livro livro){
        livros.put(livro.CdgLivro,livro);
    }

    public void salvaArquivo(Hashtable Hash, String nomeArq){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArq));
            outputStream.writeObject(Hash);
            outputStream.flush();
            outputStream.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void leArquivo(String nomeArq){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArq));
            Hashtable Hash = (Hashtable)inputStream.readObject();
            System.out.println(Hash);
            inputStream.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public void emprestaLivro(Usuario usuario , Livro livro) throws CopiaNaoDisponivelEx{
        livro.empresta();
        GregorianCalendar dataAtual = new GregorianCalendar();
        GregorianCalendar dataDev = new GregorianCalendar();
        dataDev.add(Calendar.DATE, 7);
        usuario.addLivroHist(dataAtual, dataDev, livro.getCdgLivro());
        livro.addUsuarioHist(dataAtual, dataDev, usuario.getCdgUsuario());

    }

    public void devolveLivro(Usuario usuario , Livro livro) throws NenhumaCopiaEmprestadaEx{
        int liv = usuario.getLivHist(livro.getCdgLivro());
        int usu = livro.getUsuHist(usuario.getCdgUsuario());
        GregorianCalendar dataAtual = new GregorianCalendar();
        if(dataAtual.compareTo(usuario.getDataDev(usu))==1){
            System.out.println("Voce esta atrasado na devoluçao!");
        }
        livro.devolve();
        usuario.devolucao();
        livro.removUsuHist(usu, dataAtual);
        usuario.removIndexLivHist(liv,dataAtual);
    }
    
    public String imprimeLivros(){
        if(usuarios.isEmpty()){
            System.out.println("Nao ha nenhum livro cadastrado!");
        }
        return livros.toString();
    }
    public String imprimeUsuarios(){
        if(usuarios.isEmpty()){
            System.out.println("Nao ha nenhum usuario cadastrado!");
        }
        return usuarios.toString();
    }

    public Livro getLivro(String cdgLivro) throws LivroNaoCadastradoEx{
        Livro livro = (Livro) livros.get(cdgLivro);
        if(livro == null){
            throw new LivroNaoCadastradoEx();
        }
        return livro;
    }

    public Usuario getUsuario(int cdgUsuario) throws UsuarioNaoCadastradoEx{
        Usuario usuario = (Usuario) usuarios.get(cdgUsuario);
        if(usuario == null){
            throw new UsuarioNaoCadastradoEx();
        }
        return usuario;
    }

    public Hashtable getUsuarioHashtable(){
        return usuarios;
    }
    public Hashtable getLivroHashtable(){
        return livros;
    }
}