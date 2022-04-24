package es.seresco.libreriaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.seresco.libreriaspring.model.Libro;



public interface LibroRespository extends JpaRepository<Libro,Long>{

	@Query("select l from Libro l where l.codigo=?1")
	Optional<Libro> findByCodigo(String codigo);
	
}
