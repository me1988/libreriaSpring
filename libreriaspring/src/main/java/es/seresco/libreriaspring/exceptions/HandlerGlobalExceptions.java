package es.seresco.libreriaspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class HandlerGlobalExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundEntity.class )
	public ResponseEntity<ErrorDto> notFoundException(Exception ex){
		
		ErrorDto dto = new ErrorDto();
		NotFoundEntity excepcion = (NotFoundEntity) ex;
		dto.setFecha(excepcion.getFechaError().toString());
		dto.setCodigoError(excepcion.getCodError());
		dto.setMensaje(excepcion.getMessage());
		log.info("Erro al buscar un registro en la clase...aki tendria q añadir una propiedad a la clase de la excepcion y bean");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}
	
	@ExceptionHandler(DataInconsistentException.class )
	public ResponseEntity<ErrorDto> dataInconsistentException(Exception ex){
		
		ErrorDto dto = new ErrorDto();
		DataInconsistentException excepcion = (DataInconsistentException) ex;
		dto.setFecha(excepcion.getFechaError().toString());
		dto.setCodigoError(excepcion.getCodError());
		dto.setMensaje(excepcion.getMessage());
		log.info("No coinciden el valor del paht con el id de la entidad");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}
	
	@ExceptionHandler(NumPrestamosException.class )
	public ResponseEntity<ErrorDto> numPrestamosException(Exception ex){
		
		ErrorDto dto = new ErrorDto();
		NumPrestamosException excepcion = (NumPrestamosException) ex;
		dto.setFecha(excepcion.getFechaError().toString());
		dto.setCodigoError(excepcion.getCodError());
		dto.setMensaje(excepcion.getMessage());
		log.info("No coinciden el valor del paht con el id de la entidad");
		return ResponseEntity.status(HttpStatus.CHECKPOINT).body(dto);
	}
	
	///validationconstraintexception con override o como las otras
	
}
