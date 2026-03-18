package br.com.techtask.controller;

import br.com.techtask.dto.TarefaResponseDTO;
import br.com.techtask.modelo.Tarefa;
import br.com.techtask.modelo.Usuario;
import br.com.techtask.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping
    public TarefaResponseDTO cadastrarTarefa(
            @Valid @RequestBody Tarefa novaTarefa,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        // 1. O Service guarda a tarefa e devolve a entidade preenchida (com ID, etc)
        Tarefa tarefaSalva = service.cadastrarTarefa(novaTarefa, usuarioLogado);

        // 2. Nós convertemos a Entidade para DTO antes de cuspir para a rua!
        return new TarefaResponseDTO(tarefaSalva);
    }

    @GetMapping
    public List<TarefaResponseDTO> buscarTarefas(@AuthenticationPrincipal Usuario usuarioLogado) {

        // 1. Busca as tarefas APENAS desse usuário
        List<Tarefa> minhasTarefas = service.buscarTarefa(usuarioLogado);

        // 2. Converte a lista de Entidades para uma lista de DTOs limpos e seguros
        return minhasTarefas.stream()
                .map(TarefaResponseDTO::new)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        service.deletarTarefa(id, usuarioLogado);
    }

    @PutMapping("/{id}")
    // Mudamos o retorno para TarefaResponseDTO e adicionamos o @AuthenticationPrincipal
    public TarefaResponseDTO atualizarTarefa(
            @PathVariable Long id,
            @Valid @RequestBody Tarefa tarefa,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        return service.atualizarTarefa(id, tarefa, usuarioLogado);
    }
}
