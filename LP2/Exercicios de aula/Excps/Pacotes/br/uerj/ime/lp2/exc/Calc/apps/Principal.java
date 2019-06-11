package br.uerj.ime.lp2;
import java.io.*;
import br.uerj.ime.lp2.exc.*;
public class Principal{
    public static void main(String args[])throws IOException,Erro,DivZero{
        String aux = args[0];
        String aux2 = args[1];
        double a = Double.parseDouble(aux);
        double b = Double.parseDouble(aux2);
        int c = Integer.parseInt(aux);
        int d = Integer.parseInt(aux2);
        Calc p = new Calc();
        System.out.println(p.soma(c,d));
        System.out.println(p.sub(c,d));
        System.out.println(p.mult(a,b));
        System.out.println(p.div(a,b));
    }
}