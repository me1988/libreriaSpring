package es.seresco.libreriaspring.service;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.model.Categoria;

@Validated
public interface CategoriaService {

	final static String BEAN_NAME="categoriaService";
	
	Categoria findById(@NotNull Long id) throws NotFoundEntity;
	
}
