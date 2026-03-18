package br.com.techtask.repositorio;

import br.com.techtask.modelo.Tarefa;
import br.com.techtask.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    boolean existsByTitulo(String titulo);

    // O Spring lê o nome do método e já entende o que precisa fazer!
    List<Tarefa> findByUsuario(Usuario usuario);

    // O Spring cria: SELECT * FROM tarefa WHERE id = ? AND usuario_id = ?
    Optional<Tarefa> findByIdAndUsuario(Long id, Usuario usuario);
}
