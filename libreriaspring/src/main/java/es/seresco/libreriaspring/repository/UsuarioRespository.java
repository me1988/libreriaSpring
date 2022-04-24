package es.seresco.libreriaspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosCountDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosDTO;
import es.seresco.libreriaspring.model.Usuario;
@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.codigo=?1")
	Optional<Usuario> findByCodigo(String codigo);

	//@Query("select new es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosDTO( u.usuario.codigo , u.usuario.nombre , u.usuario.apellidos ) from Prestamo u group by u.usuario.id  having count(u.usuario.id)  IN"
	//		+ "( SELECT count(p.usuario.id) from Prestamo p group by p.usuario.id) order by count(*) desc ")
	@Query("select new es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosCountDTO( u.usuario.codigo , u.usuario.nombre , u.usuario.apellidos,count(u.usuario.id )) from Prestamo u group by u.usuario.id  having count(u.usuario.id) >=ALL"
			+ "( SELECT count(p.usuario.id) from Prestamo p group by p.usuario.id) order by count(*) asc ")
	List<ResponseUsuarioNumLibrosCountDTO> findMaxLibrosLeidos();
	//filtar datos terminar mañan
	@Query("select new es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosCountDTO( u.usuario.codigo , u.usuario.nombre , u.usuario.apellidos,count(u.usuario.id )) from Prestamo u group by u.usuario.id  having count(u.usuario.id)<=ALL"
			+ "( SELECT count(p.usuario.id) from Prestamo p group by p.usuario.id) order by count(*) asc ")
	List<ResponseUsuarioNumLibrosCountDTO> findMinLibrosLeidos();
}
