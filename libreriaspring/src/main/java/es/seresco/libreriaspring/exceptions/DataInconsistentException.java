package es.seresco.libreriaspring.exceptions;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataInconsistentException extends ProyectExceptions {

	private static final long serialVersionUID = -8143168512013032361L;

	private final Date fechaError;
	private final String codError;
	
	public DataInconsistentException(String codError, String mensaje) {
		super(mensaje);
		this.fechaError = new Date();
		this.codError = codError;
	}

	
	
}
