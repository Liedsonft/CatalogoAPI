package br.com.mv.FilmeAPI.services;

import br.com.mv.FilmeAPI.models.FilmeModel;
import br.com.mv.FilmeAPI.repositories.FilmeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FilmeService {

    final
    FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public boolean existsByName(String name) {
        return filmeRepository.existsByName(name);
    }

    @Transactional
    public FilmeModel save(FilmeModel filmeModel) {
        return filmeRepository.save(filmeModel);
    }


    public Page<FilmeModel> findAll(Pageable pageable) {
        return filmeRepository.findAll(pageable);
    }

    public Optional<FilmeModel> findByName(String name) {
        return filmeRepository.findByName(name);
    }

    @Transactional
    public void delete(FilmeModel filmeModel) {
        filmeRepository.delete(filmeModel);
    }
}