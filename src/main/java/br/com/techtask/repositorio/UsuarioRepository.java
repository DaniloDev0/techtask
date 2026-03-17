package br.com.techtask.repositorio;

import br.com.techtask.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    boolean existsByEmail(String email);
    // O Spring Security vai usar esse método para achar o cara na hora do login!
    UserDetails findByEmail(String email);
}
