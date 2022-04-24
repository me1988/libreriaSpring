package es.seresco.libreriaspring.exceptions;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NotFoundEntity extends ProyectExceptions {

	private final Date fechaError;
	private final String codError;
	
	
	
	public NotFoundEntity(String codError, String mensaje) {
		super(mensaje);
		this.fechaError = new Date();
		this.codError = codError;
	}
	
}
