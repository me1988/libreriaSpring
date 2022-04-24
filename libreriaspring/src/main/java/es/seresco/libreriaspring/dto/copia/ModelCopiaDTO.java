package es.seresco.libreriaspring.dto.copia;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import es.seresco.libreriaspring.model.enumerado.Estado;
import lombok.Data;

@Data
public abstract class ModelCopiaDTO {
	
	@NotNull @Length(max=15)
	private String codigo;
	
	private Estado estado;

	private Long libroId;
}
