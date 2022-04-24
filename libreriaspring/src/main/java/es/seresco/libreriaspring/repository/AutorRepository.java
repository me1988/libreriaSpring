package es.seresco.libreriaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.libreriaspring.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
