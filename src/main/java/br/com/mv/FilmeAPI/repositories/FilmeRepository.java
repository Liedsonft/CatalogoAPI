package br.com.mv.FilmeAPI.repositories;

import br.com.mv.FilmeAPI.models.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FilmeRepository extends JpaRepository<FilmeModel, Integer> {
    boolean existsByName(String name);

    Optional<FilmeModel> findByName(String name);

}

