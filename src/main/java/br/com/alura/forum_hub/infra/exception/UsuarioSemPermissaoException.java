package br.com.alura.forum_hub.infra.exception;

public class UsuarioSemPermissaoException extends RuntimeException{
    public UsuarioSemPermissaoException(String mensagem){
        super(mensagem);
    }
}
