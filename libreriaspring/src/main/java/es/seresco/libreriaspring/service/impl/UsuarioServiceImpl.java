package es.seresco.libreriaspring.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.dto.copia.ResponseCopiaDTO;
import es.seresco.libreriaspring.dto.usuario.RequestCreateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.RequestDeleteUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.RequestUpdateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosCountDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.exceptions.ProyectExceptions;
import es.seresco.libreriaspring.mapper.UsuarioMapper;
import es.seresco.libreriaspring.model.Copia;
import es.seresco.libreriaspring.model.Usuario;
import es.seresco.libreriaspring.repository.UsuarioRespository;
import es.seresco.libreriaspring.service.LibroService;
import es.seresco.libreriaspring.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;


@Service(UsuarioService.BEAN_NAME)
@Slf4j
@Validated
@Transactional(rollbackFor =ProyectExceptions.class)//rollback  servicio b y para validationconstraintexception
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private MessageServiceImpl message;
	
	@Autowired
	private UsuarioRespository usuarioRepository;
	
	@Autowired
	private UsuarioMapper mapper;
	
	
	@Override
	public ResponseUsuarioDTO findById(@NotNull Long id) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por id", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Usuario usuario= usuarioRepository.findById(id).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		return mapper.mapEntityToDTO(usuario);
	}

	@Override
	public ResponseUsuarioDTO findByCodigo(@NotEmpty String codigo) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por codigo", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Usuario usuario= usuarioRepository.findByCodigo(codigo).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		return mapper.mapEntityToDTO(usuario);
	}

	@Override
	public List<ResponseUsuarioDTO> findAll() throws NotFoundEntity {
		
		log.info("Recuperando todos los registros de la entidad {} en el bean {}", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		List<Usuario> lista= usuarioRepository.findAll();
		if(lista.isEmpty()) {
			throw new NotFoundEntity("404",message.getValue("002"));
		}
		return mapper.mapListToDTOList(lista);
	}

	@Override
	public ResponseUsuarioDTO create(@Valid RequestCreateUsuarioDTO requestCreateUsuarioDTO) throws NotFoundEntity {
		
		log.info("Creando registro de la entidad {} en el bean {}", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Usuario usuario= mapper.mapDTOToEntity(requestCreateUsuarioDTO);
		
		usuario= usuarioRepository.save(usuario);
		
		ResponseUsuarioDTO responseUsuarioDTO= mapper.mapEntityToDTO(usuario);
		
		return responseUsuarioDTO;
	}

	@Override
	public void update(@Valid RequestUpdateUsuarioDTO requestUpdateUsuarioDTO) throws NotFoundEntity {
		
		log.info("Actualizando registro de la entidad {} en el bean {}", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Usuario usuario= mapper.mapUpdateDTOToEntity(requestUpdateUsuarioDTO);
		if(!usuarioRepository.existsById(usuario.getId()))throw new NotFoundEntity("404",message.getValue("000"));
		
		usuarioRepository.save(usuario);
	}

	@Override
	public void delete(@Valid RequestDeleteUsuarioDTO requestDeleteUsuarioDTO) throws NotFoundEntity {
		
		log.info("Borrando registro de la entidad {} en el bean {}", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		if(!usuarioRepository.existsById(requestDeleteUsuarioDTO.getId()))throw new NotFoundEntity("404",message.getValue("000"));
		usuarioRepository.deleteById(requestDeleteUsuarioDTO.getId());
		

	}

	@Override
	public Usuario findUsuarioByCodigo(@NotEmpty String codigo) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por codigo", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Usuario usuario= usuarioRepository.findByCodigo(codigo).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		return usuario;
	}

	@Override
	public List<ResponseUsuarioNumLibrosCountDTO> findUsuarioMaxLibros() throws NotFoundEntity {
		log.info("Buscando el usuario que menos libros ha leido en la entidad {} en el bean {} por codigo", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		List<ResponseUsuarioNumLibrosCountDTO> lista=usuarioRepository.findMaxLibrosLeidos();
		if(lista.isEmpty())throw new NotFoundEntity("404",message.getValue("000"));
 		return  lista ;
	}

	@Override
	public List<ResponseUsuarioNumLibrosCountDTO> findUsuarioMinLibros() throws NotFoundEntity {
		log.info("Buscando el usuario que menos libros ha leido en la entidad {} en el bean {} por codigo", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		List<ResponseUsuarioNumLibrosCountDTO> lista=usuarioRepository.findMinLibrosLeidos();
		if(lista.isEmpty())throw new NotFoundEntity("404",message.getValue("000"));
		
		/*Long longest = lista.stream()
                .mapToLong(ResponseUsuarioNumLibrosCountDTO::getCount)
                .min()
                .orElse(-1);

		List<ResponseUsuarioNumLibrosCountDTO> result = lista.stream()
                        .filter(s -> s.getCount() == longest)
                        .collect(Collectors.toList());     */

		
 		return  lista ;
	}	
	

	

}
