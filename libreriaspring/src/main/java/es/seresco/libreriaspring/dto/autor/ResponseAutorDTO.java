package es.seresco.libreriaspring.dto.autor;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ResponseAutorDTO extends ModelAutorDTO{

	@NotNull
	private Long id;
	
}
