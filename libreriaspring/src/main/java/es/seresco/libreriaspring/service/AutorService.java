package es.seresco.libreriaspring.service;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.dto.autor.ResponseAutorDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.model.Autor;
@Validated
public interface AutorService {

	final static String BEAN_NAME="autorService";
	
	Autor findById(@NotNull Long id) throws NotFoundEntity;
}
