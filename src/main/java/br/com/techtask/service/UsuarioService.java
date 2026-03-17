package br.com.techtask.service;

import br.com.techtask.exception.RecursoNaoEncontradoException;
import br.com.techtask.modelo.Usuario;
import br.com.techtask.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetamos o nosso criptógrafo aqui!

    public Usuario cadastrarUsuario(Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Erro: Já existe um usuário com esse email");
        }

        // Pega a senha limpa, embaralha, e devolve para o usuário antes de salvar!
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

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
