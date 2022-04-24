package es.seresco.libreriaspring.dto.libro;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import es.seresco.libreriaspring.model.Autor;
import es.seresco.libreriaspring.model.Categoria;
import es.seresco.libreriaspring.model.Copia;
import lombok.Data;
@Data
public class ResponseLibroDTO extends LibroModelDTO{

	@NotNull
	private Long id;
	
	
}
