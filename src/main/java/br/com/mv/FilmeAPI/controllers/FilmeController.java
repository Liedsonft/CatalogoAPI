package br.com.mv.FilmeAPI.controllers;


import br.com.mv.FilmeAPI.dtos.FilmeDto;
import br.com.mv.FilmeAPI.models.FilmeModel;
import br.com.mv.FilmeAPI.services.FilmeService;
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
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping("/filme")
public class FilmeController {
    final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveFilme(@RequestBody @Valid FilmeDto filmeDto) {
        if (filmeService.existsByName(filmeDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Filme já cadastrado!");
        }
        var filmeModel = new FilmeModel();
        BeanUtils.copyProperties(filmeDto, filmeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filmeModel));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Page<FilmeModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.findAll(pageable));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{name}")
    public ResponseEntity<Object> getOneFilme(@PathVariable(value = "name") String name) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findByName(name);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmeModelOptional.get());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteFilme(@PathVariable(value = "name") String name) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findByName(name);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
        filmeService.delete(filmeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Filme apagado com sucesso!");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{name}")
    public  ResponseEntity<Object> updatePatient (@PathVariable(value = "name") String name,
                                                  @RequestBody @Valid FilmeDto filmeDto) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findByName(name);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
        var filmeModel = filmeModelOptional.get();
        filmeModel.setName(filmeDto.getName());
        filmeModel.setDescricao(filmeDto.getDescricao());
        filmeModel.setAno(filmeDto.getAno());
        filmeModel.setDuracao(filmeDto.getDuracao());
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.save(filmeModel));
    }
}
