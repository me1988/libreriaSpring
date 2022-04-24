package es.seresco.libreriaspring.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import es.seresco.libreriaspring.model.*;
import es.seresco.libreriaspring.dto.usuario.RequestCreateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.RequestDeleteUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.RequestUpdateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosCountDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;


public interface UsuarioService {

	
	final static String BEAN_NAME="usuarioService";
	
	ResponseUsuarioDTO findById(@NotNull Long id) throws NotFoundEntity;
	
	ResponseUsuarioDTO findByCodigo(@NotEmpty String codigo) throws NotFoundEntity;
	
	Usuario findUsuarioByCodigo(@NotEmpty String codigo) throws NotFoundEntity;
	
	List<ResponseUsuarioDTO> findAll() throws NotFoundEntity;
	
	ResponseUsuarioDTO create(@Valid RequestCreateUsuarioDTO requestCreateUsuarioDTO) throws NotFoundEntity;
	
	void update(@Valid RequestUpdateUsuarioDTO  requestUpdateUsuarioDTO) throws NotFoundEntity;
	
	void delete(@Valid RequestDeleteUsuarioDTO requestDeleteUsuarioDTO) throws NotFoundEntity;

	List<ResponseUsuarioNumLibrosCountDTO> findUsuarioMaxLibros() throws NotFoundEntity;
	
	List<ResponseUsuarioNumLibrosCountDTO> findUsuarioMinLibros() throws NotFoundEntity;
}
