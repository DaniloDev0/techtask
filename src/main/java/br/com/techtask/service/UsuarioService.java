package br.com.techtask.service;

import br.com.techtask.exception.RecursoNaoEncontradoException;
import br.com.techtask.modelo.Usuario;
import br.com.techtask.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrarUsuario(Usuario usuario) {

        if (repository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Erro: Ja existe um usuario com esse email");
        }
        return repository.save(usuario);
    }

    public List<Usuario> listarTodosUsuarios() {
        return repository.findAll();
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Erro: Não é possível atualizar. Usuário de ID " + id + " não existe!");
        }
        usuario.setId(id);
        return repository.save(usuario);
    }

    public void deletarUsuario(Long id) {

        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Erro: Usuário de ID " + id + " não encontrado!");
        }
        repository.deleteById(id);
    }
}
