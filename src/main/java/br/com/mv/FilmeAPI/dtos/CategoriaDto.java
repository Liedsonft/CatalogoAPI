package br.com.mv.FilmeAPI.dtos;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
