package es.seresco.libreriaspring.dto.libro;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import es.seresco.libreriaspring.model.Autor;
import es.seresco.libreriaspring.model.Categoria;
import es.seresco.libreriaspring.model.Copia;
import lombok.Data;
@Data
public abstract class LibroModelDTO {

	@NotNull @Length(max=15)
	private String codigo;
	
	@Length(max=50)
	private String titulo;
	
	@NotNull @Length(max=15)
	private String isbn;
	
	@Length(max=50)
	private String editorial;
	
	private Long categoriaId;

	//mejor string codigo
	private Long AutorId;
	
}
