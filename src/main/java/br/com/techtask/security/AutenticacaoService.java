package br.com.techtask.security;

import br.com.techtask.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    // Esse é o método que o Spring Security vai chamar AUTOMATICAMENTE na hora do login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Como nós avisamos lá na classe Usuario que o nosso "Username" é o "Email",
        // o Spring vai passar o e-mail digitado no Postman para dentro dessa variável 'username'.
        return repository.findByEmail(username);
    }
}