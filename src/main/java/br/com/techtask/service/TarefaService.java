package br.com.techtask.service;

import br.com.techtask.dto.TarefaResponseDTO;
import br.com.techtask.exception.RecursoNaoEncontradoException;
import br.com.techtask.modelo.Tarefa;
import br.com.techtask.modelo.Usuario;
import br.com.techtask.repositorio.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;

    // No seu TarefaService, o método ficará assim:
    public Tarefa cadastrarTarefa(Tarefa novaTarefa, Usuario usuarioLogado) {

        // 1. Antes de salvar, dizemos à tarefa quem é o dono dela
        novaTarefa.setUsuario(usuarioLogado);

        // 2. O repository salva no banco e você devolve o resultado
        return repository.save(novaTarefa);
    }

    // Atualize o método para receber o usuário logado
    public List<Tarefa> buscarTarefa(Usuario usuarioLogado) {
        // Agora ele busca apenas as tarefas deste usuário específico!
        return repository.findByUsuario(usuarioLogado);
    }

    // Atualize o método de Atualizar (agora retornando o DTO!)
    public TarefaResponseDTO atualizarTarefa(Long id, Tarefa tarefaAtualizada, Usuario usuarioLogado) {
        // Mesma validação de segurança do Delete
        Tarefa tarefaExistente = repository.findByIdAndUsuario(id, usuarioLogado)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada ou você não tem permissão para atualizá-la!"));

        // Atualizamos apenas os dados permitidos
        tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
        tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
        // Se você tiver tags, atualiza aqui também: tarefaExistente.setTags(tarefaAtualizada.getTags());

        // Salva e converte para o DTO seguro!
        repository.save(tarefaExistente);
        return new TarefaResponseDTO(tarefaExistente);
    }

    // Atualize o método de Deletar
    public void deletarTarefa(Long id, Usuario usuarioLogado) {
        // Tenta achar a tarefa que tenha esse ID E que pertença a esse usuário
        Tarefa tarefa = repository.findByIdAndUsuario(id, usuarioLogado)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada ou você não tem permissão para deletá-la!"));

        repository.delete(tarefa);
    }
}
