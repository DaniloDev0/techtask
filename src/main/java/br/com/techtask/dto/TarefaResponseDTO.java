package br.com.techtask.dto;

import br.com.techtask.modelo.Tarefa;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String nomeDoDono // Em vez do utilizador inteiro, devolvemos só o nome!
) {
    // Este é um construtor mágico para facilitar a nossa vida.
    // Ele pega numa Entidade Tarefa e extrai apenas o que queremos mostrar.
    public TarefaResponseDTO(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getUsuario().getNome()
        );
    }
}