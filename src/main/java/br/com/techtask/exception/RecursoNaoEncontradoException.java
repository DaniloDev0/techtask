package br.com.techtask.exception;

// Estender RuntimeException é o que transforma essa classe em uma "bomba" que podemos lançar
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}