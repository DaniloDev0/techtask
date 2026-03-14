package br.com.techtask.controller;

import br.com.techtask.modelo.Tarefa;
import br.com.techtask.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping
    public Tarefa cadastrarTarefa(@Valid @RequestBody Tarefa novaTarefa) {
        return service.cadastrarTarefa(novaTarefa);
    }

    @GetMapping
    public List<Tarefa> buscarTarefas() {
        return service.buscarTarefa();
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        service.deletarTarefa(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id,@Valid @RequestBody Tarefa tarefa) {
        tarefa.setId(id);
       return service.atualizarTarefa(id, tarefa);
    }
}
