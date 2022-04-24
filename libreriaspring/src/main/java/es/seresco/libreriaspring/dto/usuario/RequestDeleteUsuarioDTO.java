package es.seresco.libreriaspring.dto.usuario;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class RequestDeleteUsuarioDTO {

	@NotNull
	private Long id;
	
	private Boolean flag;
	
}
