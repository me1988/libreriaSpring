package es.seresco.libreriaspring.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.libreriaspring.dto.usuario.RequestCreateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.RequestUpdateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioDTO;
import es.seresco.libreriaspring.model.*;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	@Mapping(source = "codigo", target = "codigo")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "apellidos", target = "apellidos")
	Usuario mapDTOToEntity(RequestCreateUsuarioDTO requestCreateUsuarioDTO);

	
	
	@Mapping(source = "id", target = "id")
	@InheritConfiguration(name = "mapDTOToEntity")
	ResponseUsuarioDTO mapEntityToDTO(Usuario usuario);
	Usuario mapUpdateDTOToEntity(RequestUpdateUsuarioDTO requestUpdateUsuarioDTO);
	List<ResponseUsuarioDTO> mapListToDTOList(List<Usuario> listaUsuario);
	
}
