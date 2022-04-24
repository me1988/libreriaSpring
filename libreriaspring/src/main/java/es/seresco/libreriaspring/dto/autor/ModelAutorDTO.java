package es.seresco.libreriaspring.dto.autor;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
@Data
public abstract class ModelAutorDTO {

	@NotNull @Length(max=15)
	private String codigo;
	
	@Length(max=25)
	private String nombre;
	
	@Length(max=25)
	private String apellidos;
	
	@Length(max=25)
	private String nacinalidad;
}
