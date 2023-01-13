package br.com.mv.FilmeAPI.controllers;

import br.com.mv.FilmeAPI.dtos.CategoriaDto;
import br.com.mv.FilmeAPI.models.CategoriaModel;
import br.com.mv.FilmeAPI.services.CategorizeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categoria")

public class CategoriaController {

    private final CategorizeService categorizeService;

    public CategoriaController(CategorizeService categorizeService) {
        this.categorizeService = categorizeService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto) {
        if (categorizeService.existsByName(categoriaDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Categoria já cadastrada!");
        }

        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaDto,categoriaModel );
        return ResponseEntity.status(HttpStatus.CREATED).body(categorizeService.save(categoriaModel));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Page<CategoriaModel>> getAllCategoria(@PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(categorizeService.findAll(pageable));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{name}")
    public ResponseEntity<Object> getOneFilme(@PathVariable(value = "name") String name) {
        Optional<br.com.mv.FilmeAPI.models.CategoriaModel> categoriaModelOptional = categorizeService.findByName(name);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoriaModelOptional.get());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable(value = "name") String name) {
        Optional<CategoriaModel> categoriaModelOptional = categorizeService.findByName(name);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }
        categorizeService.delete(categoriaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Categoria apagada com sucesso!");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{name}")
    public  ResponseEntity<Object> updateCategoria (@PathVariable(value = "name") String name,
                                                  @RequestBody @Valid CategoriaDto categorias) {
        Optional<CategoriaModel> categoriaModelOptional = categorizeService.findByName(name);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }
        br.com.mv.FilmeAPI.models.CategoriaModel categoriaModel;
        categoriaModel = categoriaModelOptional.get();
        categoriaModel.setName(categorias.getName());
        return ResponseEntity.status(HttpStatus.OK).body(categorizeService.save(categoriaModel));
    }
}
