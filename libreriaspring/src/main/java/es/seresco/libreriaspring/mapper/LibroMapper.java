package es.seresco.libreriaspring.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import es.seresco.libreriaspring.dto.libro.RequestCreateLibroDTO;
import es.seresco.libreriaspring.dto.libro.RequestUpdateLibroDTO;
import es.seresco.libreriaspring.dto.libro.ResponseLibroDTO;
import es.seresco.libreriaspring.model.Libro;


@Mapper(componentModel = "spring")
public interface LibroMapper {

	@Mapping(source = "codigo", target = "codigo")
	@Mapping(source = "titulo", target = "titulo")
	@Mapping(source = "isbn", target = "isbn")
	@Mapping(source = "editorial", target = "editorial")
	@Mapping(target = "idCategoria", ignore=true)
	@Mapping(target = "idAutor", ignore=true)
	Libro mapDTOToEntity(RequestCreateLibroDTO requestCreateLibroDTO);

	
	
	@Mapping(source = "id", target = "id")
	@InheritConfiguration(name = "mapDTOToEntity")
	ResponseLibroDTO mapEntityToDTO(Libro libro);
	Libro mapUpdateDTOToEntity(RequestUpdateLibroDTO requestUpdateLibroDTO);
	List<ResponseLibroDTO> mapListToDTOList(List<Libro> listaLibros);
}
