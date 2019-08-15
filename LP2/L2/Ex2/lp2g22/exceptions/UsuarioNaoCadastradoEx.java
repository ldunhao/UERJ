package lp2g22.exceptions;

public class UsuarioNaoCadastradoEx extends Exception{
    public UsuarioNaoCadastradoEx(){
        super("Este usuario nao esta cadastrado!");
    }
}