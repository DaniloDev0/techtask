package br.com.techtask.modelo;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    // 🪄 A MÁGICA: @ElementCollection avisa o Hibernate para criar uma tabela extra só para as Tags!
    @ElementCollection
    private Set<String> tags = new HashSet<>(); // Usamos HashSet porque o Set não aceita itens repetidos!

    // Construtor vazio obrigatório para o Spring
    public Tarefa() {
    }

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    // --- GETTERS E SETTERS ---
    // Dica de Sênior: No IntelliJ, você pode apagar esses métodos abaixo,
    // apertar Alt + Insert (ou botão direito > Generate > Getter and Setter)
    // e deixar a IDE criar todos eles para você em 1 segundo!

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Set<String> getTags() { return tags; }
    public void setTags(Set<String> tags) { this.tags = tags; }
}