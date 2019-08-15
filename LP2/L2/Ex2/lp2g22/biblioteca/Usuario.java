package lp2g22.biblioteca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class Usuario extends Pessoa implements Serializable{
    protected String endereco;
    protected int codigoUsuario;
    protected int qntdLivro;
    protected ArrayList<Emprestimo> Historico;  
    private String cdg;
    private GregorianCalendar dataEmp;

    public Usuario( String nome, int dia, int mes, int ano, String end, int cdgU){
        super(nome, dia, mes, ano);
        this.endereco = end;
        this.codigoUsuario = cdgU;
        Historico = new ArrayList<Emprestimo>();
    }

    public void addLivroHist(GregorianCalendar dataEmp, GregorianCalendar dataDev, String cdgLivro ){
        Historico.add(new Emprestimo(dataEmp, dataDev, cdgLivro));
        this.cdg = cdgLivro;
        this.dataEmp = dataEmp;
    }

    public String getEnd(){
        return this.endereco;
    }

    public int getCdgUsuario(){
        return this.codigoUsuario;
    }

    public void devolucao(){
        if(qntdLivro == 0){
            System.out.println("O usuario nao possui livros emprestados.");
        }else{
            qntdLivro--;
        }
    }
    
    public GregorianCalendar getDataDev(int i){
        return Historico.get(i).dataDev;
    }
    
    public int getLivHist(String codigoLivro){
        for(int i = 0; i < Historico.size(); i++){
            Emprestimo tmpEmprest = (Emprestimo) Historico.get(i);
            if(tmpEmprest.cdgLivro.equals(codigoLivro)){
                return i;
            }
        }
        return -1;
    }

    public void removIndexLivHist(int i, GregorianCalendar data){
        Emprestimo tmpEmp = new Emprestimo(dataEmp , data , cdg);
        Historico.set(i, tmpEmp);
    }

    public String imprimeHistorico(){
        String strHistorico = "--->  Historico <---\n";
        for(int i = 0; i < Historico.size(); i++){
            strHistorico += Historico.get(i) + "\n";
        }
        return strHistorico;
    }
    @Override
    public String toString(){
        return String.format("Nome: " + nome + "\n"
                + "Data de Nascimento: " + getDataNasc().get(Calendar.DATE) + "\\" + getDataNasc().get(Calendar.MONTH) + "\\" + getDataNasc().get(Calendar.YEAR) + "\n"
                + "Endereco: " + endereco + "\n"
                + "Codigo do Usuario: " + codigoUsuario + "\n"
                + "Quantidade de Livros: " + qntdLivro + "\n"
                + imprimeHistorico() + "\n");
    }

}