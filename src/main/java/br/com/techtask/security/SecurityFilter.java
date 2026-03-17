package br.com.techtask.security;

import br.com.techtask.repositorio.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // O @Component diz pro Spring: "Carrega esse segurança na memória pra mim"
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Pega o Token que vem no Cabeçalho (Header) do Postman
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // 2. Passa na máquina de ler crachás e pega o email do dono
            var subject = tokenService.getSubject(tokenJWT);

            // 3. Vai no banco de dados e busca o usuário por esse email
            var usuario = repository.findByEmail(subject);

            // 4. Força o Spring Security a aceitar que esse usuário está autenticado e liberado!
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 5. Continua o fluxo da requisição (se não tinha token, ele barra logo em seguida. Se tinha, ele deixa passar pro Controller).
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para extrair o token limpinho do cabeçalho
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", ""); // O mercado usa a palavra "Bearer " antes do token, nós tiramos ela aqui.
        }
        return null;
    }
}