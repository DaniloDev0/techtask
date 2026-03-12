package br.com.techtask.service;

import br.com.techtask.modelo.Tarefa;
import br.com.techtask.repositorio.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;

    public Tarefa cadastrarTarefa(Tarefa novaTarefa) {

        if (repository.existsByTitulo(novaTarefa.getTitulo())) {
           throw new RuntimeException("Erro: Ja existe uma tarefa com esse titulo");
        }
        return repository.save(novaTarefa);
    }

    public List<Tarefa> buscarTarefa() {
        return repository.findAll();
    }

    public Tarefa atualizarTarefa(long id, Tarefa tarefaAtualizada) {
        tarefaAtualizada.setId(id);
        return repository.save(tarefaAtualizada);
    }

    public void deletarTarefa(long id) {
        repository.deleteById(id);
    }
}
