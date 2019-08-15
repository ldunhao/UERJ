package lp2g22.biblioteca;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class EmprestadoPara implements Serializable{
    protected GregorianCalendar dataEmp;
    protected GregorianCalendar dataDev;
    protected int codigoUsuario;

    public EmprestadoPara(GregorianCalendar dataEmp, GregorianCalendar dataDev, int codigoUsuario){
        this.dataEmp = dataEmp;
        this.dataDev = dataDev;
        this.codigoUsuario = codigoUsuario;
    }

    @Override

    public String toString(){
        return String.format("Codigo do Usuario: " + codigoUsuario + "\n" +
               "Data de Emprestimo: " + dataEmp.get(Calendar.DATE) + "/" + dataEmp.get(Calendar.MONTH) + "/" + dataEmp.get(Calendar.YEAR) + "\n" +
               "Data de Devolucao: " + dataDev.get(Calendar.DATE) + "/" + dataDev.get(Calendar.MONTH) + "/" + dataDev.get(Calendar.YEAR) + "\n");

    }
}