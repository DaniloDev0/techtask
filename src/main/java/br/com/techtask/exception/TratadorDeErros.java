package br.com.techtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DadosErro> TratadorDeErros(RuntimeException ex) {

        DadosErro erroFormatado = new DadosErro(ex.getMessage());
        return ResponseEntity.badRequest().body(erroFormatado);
    }
    public record DadosErro(String message) {

    }
    // 1. Avisamos o Advogado para capturar os erros do "Segurança" (Validação do Spring)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErroValidacao(MethodArgumentNotValidException ex) {

        // Pega a lista interna do Spring com todos os campos que falharam nas regras
        List<FieldError> erros = ex.getFieldErrors();

        // Transforma a lista complexa do Spring na nossa listinha simples e bonita
        // (Isso é Java Avançado usando Streams, muito comum no mercado!)
        List<DadosErroValidacao> errosFormatados = erros.stream()
                .map(DadosErroValidacao::new)
                .toList();

        return ResponseEntity.badRequest().body(errosFormatados);
    }

    // O radar agora escuta a nossa nova exceção
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<DadosErro> tratarErro404(RecursoNaoEncontradoException ex) {
        DadosErro erroFormatado = new DadosErro(ex.getMessage());

        // Em vez de badRequest(), usamos status(HttpStatus.NOT_FOUND) para devolver o código 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroFormatado);
    }

    // 2. Criamos um novo 'Molde' (record) específico para erros de digitação.
    // Ele vai mostrar o NOME do campo e a MENSAGEM que escrevemos lá na Entidade.
    public record DadosErroValidacao(String campo, String mensagem) {

        // Esse é um construtor que facilita pegar o erro do Spring e jogar no nosso molde
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }


}
