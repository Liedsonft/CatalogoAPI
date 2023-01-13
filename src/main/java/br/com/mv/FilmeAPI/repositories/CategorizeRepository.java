package br.com.mv.FilmeAPI.repositories;

import br.com.mv.FilmeAPI.models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorizeRepository extends JpaRepository<CategoriaModel, Integer> {
    boolean existsByName(String name);

    Optional<CategoriaModel> findByName(String name);
}
