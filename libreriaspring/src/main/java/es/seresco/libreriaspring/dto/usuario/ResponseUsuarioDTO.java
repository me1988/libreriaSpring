package es.seresco.libreriaspring.dto.usuario;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ResponseUsuarioDTO extends UsuarioModelDTO {

	@NotNull
	private Long id;
	
}
