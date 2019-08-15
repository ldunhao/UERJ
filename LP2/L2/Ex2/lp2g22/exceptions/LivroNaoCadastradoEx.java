package lp2g22.exceptions;

public class LivroNaoCadastradoEx extends Exception{
    public LivroNaoCadastradoEx(){
        super("Este livro nao esta cadastrado!");
    }
}