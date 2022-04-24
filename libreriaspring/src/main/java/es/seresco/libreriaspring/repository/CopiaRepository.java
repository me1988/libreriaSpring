package es.seresco.libreriaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.seresco.libreriaspring.model.Copia;


@Repository
public interface CopiaRepository extends JpaRepository< Copia , Long>{

	@Query("select c from Copia c where c.codigo=?1")
	Optional<Copia> findByCodigo(String codigo);
}
