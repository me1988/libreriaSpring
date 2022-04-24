package es.seresco.libreriaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.libreriaspring.model.Categoria;

@Repository
public interface CategoriaRespository extends JpaRepository<Categoria, Long>{

}
