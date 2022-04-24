package es.seresco.libreriaspring.dto.usuario;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UsuarioModelDTO {

	@NotNull @Length(max=15)
	private String codigo;
	
	@NotNull @Length(max=25)
	private String nombre;
	
	@NotNull @Length(max=25)
	private String apellidos;
	
}
