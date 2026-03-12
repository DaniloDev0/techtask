package br.com.techtask.repositorio;

import br.com.techtask.modelo.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    boolean existsByTitulo(String titulo);
}
