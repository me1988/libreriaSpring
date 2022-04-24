package es.seresco.libreriaspring.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.libreriaspring.dto.prestamo.RequestCreatePrestamoDTO;
import es.seresco.libreriaspring.dto.prestamo.ResponsePrestamoDTO;
import es.seresco.libreriaspring.model.Prestamo;


@Mapper(componentModel = "spring")
public interface PrestamoMapper {

	
	@Mapping(target = "copia", ignore=true)
	@Mapping(target = "usuario", ignore=true)
	Prestamo mapDTOToEntity(RequestCreatePrestamoDTO requestCreatePrestamoDTO);

	
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	@Mapping(source = "id", target = "id")
	@InheritConfiguration(name = "mapDTOToEntity")
	ResponsePrestamoDTO mapEntityToDTO(Prestamo prestamo);
	
}
