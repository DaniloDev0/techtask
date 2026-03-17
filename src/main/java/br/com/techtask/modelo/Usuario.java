package br.com.techtask.modelo;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    // (Lembre-se de usar o Alt + Insert para gerar o Getter e o Setter dela!)

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // ---------------------------------------------------------
    // MÉTODOS OBRIGATÓRIOS DO SPRING SECURITY (USER DETAILS)
    // ---------------------------------------------------------

    // 1. Controle de Permissões (Roles). Por enquanto, todo mundo é um usuário comum.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 2. O Spring pergunta: "Onde está a senha desse cara?"
    @Override
    public String getPassword() {
        return this.senha; // Apontamos para o nosso atributo 'senha'
    }

    // 3. O Spring pergunta: "O que ele usa para fazer login?"
    @Override
    public String getUsername() {
        return this.email; // ATENÇÃO AQUI: Para o Spring, o "Username" é o nosso E-MAIL!
    }

    // 4. A conta está expirada? (true = não)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 5. A conta está bloqueada? (true = não)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 6. As credenciais (senha) estão expiradas? (true = não)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 7. O usuário está ativo? (true = sim)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
