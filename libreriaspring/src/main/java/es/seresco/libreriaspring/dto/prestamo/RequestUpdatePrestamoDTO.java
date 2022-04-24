package es.seresco.libreriaspring.dto.prestamo;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestUpdatePrestamoDTO extends PrestamoModelDTO{


	@NotNull
	private Long id;
	
}
