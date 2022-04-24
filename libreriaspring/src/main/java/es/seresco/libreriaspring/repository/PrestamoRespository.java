package es.seresco.libreriaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.seresco.libreriaspring.model.Prestamo;
@Repository
public interface PrestamoRespository extends JpaRepository<Prestamo, Long>{

	@Query("Select count(p.id) from Prestamo p where p.usuario.id=?1 and p.fechaFin=null")
	int countPrestamos(Long id);
	
	@Query(" Select p from Prestamo p where p.usuario.codigo=?1 and p.copia.codigo=?2 and p.fechaFin=null")
	Optional<Prestamo> prestamoExistByUsuarioAndCopia(String codigoUsuario, String codigoCopia);

}
