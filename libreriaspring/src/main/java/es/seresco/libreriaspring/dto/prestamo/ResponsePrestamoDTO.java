package es.seresco.libreriaspring.dto.prestamo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePrestamoDTO extends PrestamoModelDTO {

	
	@NotNull
	private Long id;
	
	private Date fechaInicio;
	
	private Date fechaFin;
}
