package br.com.techtask.dto;

import br.com.techtask.modelo.Usuario;

// O record define o formato do JSON de saída (Apenas id e nome!)
public record UsuarioResponseDTO(Long id, String nome) {

    // Um construtor inteligente que recebe a Entidade do banco e transforma no DTO
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome());
    }
}
