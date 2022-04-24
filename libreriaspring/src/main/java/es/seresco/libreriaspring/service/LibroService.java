package es.seresco.libreriaspring.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import es.seresco.libreriaspring.model.Libro;
import es.seresco.libreriaspring.dto.libro.RequestCreateLibroDTO;
import es.seresco.libreriaspring.dto.libro.RequestDeleteLibroDTO;
import es.seresco.libreriaspring.dto.libro.RequestUpdateLibroDTO;
import es.seresco.libreriaspring.dto.libro.ResponseLibroDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;

@Validated
public interface LibroService {

	final static String BEAN_NAME="libroService";
	
    Libro findLibroById(@NotNull Long id) throws NotFoundEntity;
	
	ResponseLibroDTO findById(@NotNull Long id) throws NotFoundEntity;
	
	ResponseLibroDTO findByCodigo(@NotEmpty String codigo) throws NotFoundEntity;
	
	List<ResponseLibroDTO> findAll() throws NotFoundEntity;
	
	ResponseLibroDTO create(@Valid RequestCreateLibroDTO requestCreateLibroDTO) throws NotFoundEntity;
	
	void update(@Valid RequestUpdateLibroDTO  RequestUpdateLibroDTO) throws NotFoundEntity;
	
	void delete(@Valid RequestDeleteLibroDTO  RequestDeleteLibroDTO) throws NotFoundEntity;
}
