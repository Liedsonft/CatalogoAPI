package br.com.mv.FilmeAPI.config.Security.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "DB_FILME")
public class FilmeModel  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String name;
    @Column(nullable = false, length = 254)
    private String descricao;
    @Column(nullable = false, length = 4)
    private String ano;
    @Column(nullable = false, length = 12)
    private String duracao;

    @ManyToMany
    @JoinTable(name = "TB_FILME_CATEGORIAS",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<CategoriaModel> categorias;
    public FilmeModel(){

    }

    public List<CategoriaModel> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaModel> categorias) {
        this.categorias = categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}


