package com.gabriel.music.redesocial.service.Exceptions;

public class ErrorDeleteFileException extends Exception{
    public ErrorDeleteFileException() {
        super("Erro ao tentar deletar o arquivo");
    }
}
