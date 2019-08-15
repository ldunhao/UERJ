package lp2g22.biblioteca;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class Emprestimo implements Serializable{
    protected GregorianCalendar dataEmp;
    protected GregorianCalendar dataDev;
    protected String cdgLivro;

    public Emprestimo(GregorianCalendar dataEmp, GregorianCalendar dataDev, String cdgLivro){
        this.dataEmp = dataEmp;
        this.dataDev = dataDev;
        this.cdgLivro = cdgLivro;
    }

    @Override

    public String toString(){
        return String.format("Codigo do Livro: " + cdgLivro + "\n" +
               "Data de Emprestimo: " + dataEmp.get(Calendar.DATE) + "/" + dataEmp.get(Calendar.MONTH) + "/" + dataEmp.get(Calendar.YEAR) + "\n" +
               "Data de Devolucao: " + dataDev.get(Calendar.DATE) + "/" + dataDev.get(Calendar.MONTH) + "/" + dataDev.get(Calendar.YEAR) + "\n");

    }
}