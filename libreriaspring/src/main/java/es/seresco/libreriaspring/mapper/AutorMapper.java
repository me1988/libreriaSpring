package es.seresco.libreriaspring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.libreriaspring.dto.autor.ResponseAutorDTO;
import es.seresco.libreriaspring.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "codigo", target = "codigo")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "apellidos", target = "apellidos")
	@Mapping(source = "nacinalidad", target = "nacinalidad")
	ResponseAutorDTO mapEntityToDTO(Autor autor);
}
