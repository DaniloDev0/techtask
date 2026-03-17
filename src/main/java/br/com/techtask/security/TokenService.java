package br.com.techtask.security;

import br.com.techtask.modelo.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Essa é a chave mestre da sua API. (No mundo real, isso fica escondido!)
    private String secret = "minha-chave-super-secreta";

    public String gerarToken(Usuario usuario) {
        try {
            // O algoritmo assina o token com a nossa chave mestre
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API TechTask") // Quem está emitindo o crachá
                    .withSubject(usuario.getEmail()) // Para quem é o crachá (O e-mail do Danilo)
                    .withExpiresAt(dataExpiracao()) // Data de validade (Ninguém quer um crachá eterno)
                    .sign(algoritmo); // Assina e finaliza!

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    // Novo método: O Leitor de Crachás
    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret); // Usa a mesma senha mestra!
            return JWT.require(algoritmo)
                    .withIssuer("API TechTask")
                    .build()
                    .verify(tokenJWT) // Verifica se o token é válido e não está expirado
                    .getSubject(); // Devolve o e-mail (danilo@email.com)
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    // Regra de negócio: O token vale por 2 horas a partir de agora
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}