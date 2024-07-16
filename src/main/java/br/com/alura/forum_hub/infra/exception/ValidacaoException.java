package br.com.alura.forum_hub.infra.exception;

import java.security.PublicKey;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
