package lp2g22.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import lp2g22.exceptions.*;


public class Livro implements Serializable{
    protected String CdgLivro;
    protected String TitLivro;
    protected String Categoria;
    protected int Qntd;
    protected int LivrosEmpr;
    protected ArrayList<EmprestadoPara> Historico;
    private GregorianCalendar dataEmp;
    private int cdg;

    public Livro(String cdgLivro, String titLivro, String catLivro, int qntdLivro, int LivEmp){
        this.CdgLivro = cdgLivro;
        this.TitLivro = titLivro;
        this.Categoria = catLivro;
        this.Qntd = qntdLivro;
        this.LivrosEmpr = LivEmp;
        Historico = new ArrayList<EmprestadoPara>();
    }

    public Livro(String tituloLivro){
        this.TitLivro = tituloLivro;
    }
   
    public void empresta() throws CopiaNaoDisponivelEx{
        if(LivrosEmpr <= 0){
            throw new CopiaNaoDisponivelEx();
        }else{
            LivrosEmpr--;
        }
    }

    public void devolve() throws NenhumaCopiaEmprestadaEx{
        if(LivrosEmpr == 0){
            throw new NenhumaCopiaEmprestadaEx();
        }else{
            LivrosEmpr++;
        }
    }

    public void addUsuarioHist(GregorianCalendar dataEmp, GregorianCalendar dataDev, int codigoUsuario){
        Historico.add(new EmprestadoPara(dataEmp, dataDev, codigoUsuario));
        this.dataEmp = dataEmp;
        this.cdg = codigoUsuario;
    }

    public int getUsuHist(int codigoUsuario){
        for (int i = 0; i < Historico.size(); i++) {
            EmprestadoPara tmpEmpPara = (EmprestadoPara) Historico.get(i);
            if(tmpEmpPara.codigoUsuario == codigoUsuario){
                return i;
            }
        }
        return -1;
    }

    public void removUsuHist(int i, GregorianCalendar data){
        EmprestadoPara tmpEmp = new EmprestadoPara(dataEmp , data ,cdg);
        Historico.set(i, tmpEmp);
    }

    public GregorianCalendar getDataDev(int i){
        return Historico.get(i).dataDev;
    }

    public String getCdgLivro(){
        return CdgLivro;
    }

    public String getTitLivro(){
        return TitLivro;
    }

    public String getCategoria(){
        return Categoria;
    }

    public int getQntd(){
        return Qntd;
    }

    public int getLivroEmpr(){
        return LivrosEmpr;
    }

    @Override
    public String toString(){
        return  String.format("Codigo do Livro: " + CdgLivro+ "\n"
                + "Titulo: " + TitLivro + "\n"
                + "Categoria: " + Categoria + "\n"
                + "Quantidade de Livros: " + Qntd + "\n"
                + "Quantidade de Livros para emprestimo: " + LivrosEmpr + "\n");
    }

}