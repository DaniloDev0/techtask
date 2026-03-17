package br.com.techtask.controller;

import br.com.techtask.dto.DadosAutenticacao;
import br.com.techtask.modelo.Usuario;
import br.com.techtask.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);

            // 1. Pegamos o usuário que o Spring acabou de validar no banco
            Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

            // 2. Mandamos a fábrica gerar um token para esse usuário
            String tokenJWT = tokenService.gerarToken(usuarioLogado);

            // 3. Devolvemos o token na tela do Postman! (No futuro, colocaremos num DTO bonitinho)
            return ResponseEntity.ok(tokenJWT);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("O motivo do erro foi: " + e.getMessage());
        }
    }
}