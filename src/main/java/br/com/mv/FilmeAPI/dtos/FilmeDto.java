package br.com.mv.FilmeAPI.dtos;


import br.com.mv.FilmeAPI.models.CategoriaModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FilmeDto {

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 250)
    private String descricao;

    @NotBlank
    private String ano;

    @NotBlank
    @Size(max = 12)
    private String duracao;

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
