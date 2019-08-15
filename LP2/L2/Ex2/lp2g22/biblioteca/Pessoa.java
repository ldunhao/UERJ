package lp2g22.biblioteca;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.Serializable;

public class Pessoa implements Serializable {
    final String nome;
    final GregorianCalendar dataNasc; 
    
    
    public Pessoa(String nome, int dia, int mes , int ano){
        this.nome = nome;
        this.dataNasc = new GregorianCalendar(ano,mes,dia);
    }

    public String getNome(){
        return this.nome;
    }

    public GregorianCalendar getDataNasc(){
        return this.dataNasc;
    }

    @Override

    public String toString(){
        return String.format("Nome: " + getNome() + "\n" +
               "Data de Nascimento: " + dataNasc.get(Calendar.DATE) + "/" + dataNasc.get(Calendar.MONTH) + "/" + dataNasc.get(Calendar.YEAR) + "\n");
    }

}