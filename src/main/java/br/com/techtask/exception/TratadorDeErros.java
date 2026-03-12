package br.com.techtask.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler (RuntimeException.class)
    public ResponseEntity<DadosErro> TratadorDeErros(RuntimeException ex) {

        DadosErro erroFormatado =new DadosErro(ex.getMessage());
        return ResponseEntity.badRequest().body(erroFormatado);
    }

    public record DadosErro(String message){

    }


}
