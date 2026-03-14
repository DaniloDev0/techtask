package br.com.techtask.controller;

import br.com.techtask.dto.UsuarioRequestDTO;
import br.com.techtask.dto.UsuarioResponseDTO;
import br.com.techtask.modelo.Usuario;
import br.com.techtask.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponseDTO cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {

        // 1. Pegamos os dados do DTO seguro e colocamos na Entidade
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());

        // 2. Mandamos o Gerente salvar a Entidade no banco
        Usuario usuarioSalvo = usuarioService.cadastrarUsuario(novoUsuario);

        // 3. Retornamos o dublê de saída (que nós criamos na missão anterior!)
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarTodos() {
        // 1. Pega os usuários "crus" do banco
        List<Usuario> usuariosDoBanco = usuarioService.listarTodosUsuarios();

        // 2. Transforma cada um no nosso dublê seguro usando a esteira mágica (.stream)
        return usuariosDoBanco.stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}
