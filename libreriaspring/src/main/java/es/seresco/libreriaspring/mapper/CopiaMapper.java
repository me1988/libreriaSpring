package es.seresco.libreriaspring.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import  es.seresco.libreriaspring.dto.copia.*;
import  es.seresco.libreriaspring.model.*;

@Mapper(componentModel = "spring")
public interface CopiaMapper {

	
	@Mapping(source = "codigo", target = "codigo")
	@Mapping(source = "estado", target = "estado")
	@Mapping(target = "idLibro", ignore=true)
	Copia mapDTOToEntity(RequestCreateCopiaDTO requestCreateCopiaDTO);

	
	
	@Mapping(source = "id", target = "id")
	@InheritConfiguration(name = "mapDTOToEntity")
	ResponseCopiaDTO mapEntityToDTO(Copia copia);
	Copia mapUpdateDTOToEntity(RequestUpdateCopiaDTO requestUpdateCopiaDTO);
	List<ResponseCopiaDTO> mapListToDTOList(List<Copia> listaCopia);
}
