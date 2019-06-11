package br.uerj.ime.lp2;
public class DivZero extends Exception{
    @Override 
    public String toString(){
        return "Dividindo por zero";
    }
}