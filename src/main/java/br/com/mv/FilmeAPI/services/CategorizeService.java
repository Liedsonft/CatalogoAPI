package br.com.mv.FilmeAPI.services;

import br.com.mv.FilmeAPI.models.CategoriaModel;
import br.com.mv.FilmeAPI.repositories.CategorizeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategorizeService {
    private final CategorizeRepository categorizeRepository;

    public CategorizeService(CategorizeRepository categorizeRepository) {
        this.categorizeRepository = categorizeRepository;}

    public boolean existsByName(String name) {
        return categorizeRepository.existsByName(name);
    }

    public Optional<CategoriaModel> findByName(String name) {
        return categorizeRepository.findByName(name);
    }
    public Page<CategoriaModel> findAll(Pageable pageable) {
        return categorizeRepository.findAll(pageable);
    }

    @Transactional
    public void delete(CategoriaModel categoriaModel) {
        categorizeRepository.delete(categoriaModel);
    }
    @Transactional
    public CategoriaModel save(CategoriaModel categoriaModel) {
        return categorizeRepository.save(categoriaModel);
    }
}

