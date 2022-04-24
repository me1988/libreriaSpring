package es.seresco.libreriaspring.exceptions;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumPrestamosException extends ProyectExceptions {

	
	private static final long serialVersionUID = -6415057693064154886L;

	private final Date fechaError;
	private final String codError;
	
	public NumPrestamosException(String codError, String mensaje) {
		super(mensaje);
		this.fechaError = new Date();
		this.codError = codError;
	}
	
}
