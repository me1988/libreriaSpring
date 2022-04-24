package es.seresco.libreriaspring.dto.libro;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestUpdateLibroDTO extends LibroModelDTO {

	@NotNull
	private Long id;
	
}
