package es.seresco.libreriaspring.service.impl;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.dto.copia.RequestCreateCopiaDTO;
import es.seresco.libreriaspring.dto.copia.RequestDeleteCopiaDTO;
import es.seresco.libreriaspring.dto.copia.RequestUpdateCopiaDTO;
import es.seresco.libreriaspring.dto.copia.ResponseCopiaDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.exceptions.ProyectExceptions;
import es.seresco.libreriaspring.mapper.*;
import es.seresco.libreriaspring.model.Copia;

import es.seresco.libreriaspring.repository.CopiaRepository;
import es.seresco.libreriaspring.service.CopiaService;
import es.seresco.libreriaspring.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Service(CopiaService.BEAN_NAME)
@Slf4j
@Validated
@Transactional(rollbackFor =ProyectExceptions.class)
public class CopiaServiceImpl implements CopiaService{
	
	@Autowired
	private CopiaRepository copiaRepository;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private MessageServiceImpl message;
	
	@Autowired
	private CopiaMapper copiaMapper;
	

	
	
	@Override
	public ResponseCopiaDTO findById(@NotNull Long id) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por id", Copia.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Copia copia= copiaRepository.findById(id).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		ResponseCopiaDTO responseCopiaDTO= copiaMapper.mapEntityToDTO(copia);
		
		responseCopiaDTO.setLibroId(copia.getId());
		
		return responseCopiaDTO;
	}

	@Override
	public ResponseCopiaDTO findByCodigo(@NotEmpty String codigo) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por codigo", Copia.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Copia copia= copiaRepository.findByCodigo(codigo).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		ResponseCopiaDTO responseCopiaDTO= copiaMapper.mapEntityToDTO(copia);
		
		responseCopiaDTO.setLibroId(copia.getId());
		
		return responseCopiaDTO;
	}

	@Override
	public List<ResponseCopiaDTO> findAll() throws NotFoundEntity {
		log.info("Recuperando todos los registros de la entidad {} en el bean {}", Copia.class.getSimpleName(),LibroService.BEAN_NAME );
		List<Copia> lista= copiaRepository.findAll();
		if(lista.isEmpty()) {
			throw new NotFoundEntity("404",message.getValue("002"));
		}
		return copiaMapper.mapListToDTOList(lista);
	}
		
	
	@Override
	public ResponseCopiaDTO create(@Valid RequestCreateCopiaDTO requestCreateCopiaDTO) throws NotFoundEntity {
		log.info("Creando registro de la entidad {} en el bean {}", Copia.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Copia copia= copiaMapper.mapDTOToEntity(requestCreateCopiaDTO);
		
		copia.setIdLibro(libroService.findLibroById(requestCreateCopiaDTO.getLibroId()));
		
		copia= copiaRepository.save(copia);
		
		ResponseCopiaDTO responseCopiaDTO= copiaMapper.mapEntityToDTO(copia);
		responseCopiaDTO.setLibroId(copia.getIdLibro().getId());
		
		return responseCopiaDTO;
	}

	@Override
	public void update(@Valid RequestUpdateCopiaDTO requestUpdateCopiaDTO) throws NotFoundEntity {
		log.info("Actualizando registro de la entidad {} en el bean {}", Copia.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Copia copia= copiaMapper.mapUpdateDTOToEntity(requestUpdateCopiaDTO);
		
		if(!copiaRepository.existsById(copia.getId()))throw new NotFoundEntity("404",message.getValue("000"));
		
		copia.setIdLibro(libroService.findLibroById(requestUpdateCopiaDTO.getLibroId()));
		
		copiaRepository.save(copia);
		
	}

	@Override
	public void delete(@Valid RequestDeleteCopiaDTO requestDeleteCopiaDTO) throws NotFoundEntity {
		log.info("Borrando registro de la entidad {} en el bean {}", Copia.class.getSimpleName(),LibroService.BEAN_NAME );
		if(!copiaRepository.existsById(requestDeleteCopiaDTO.getId()))throw new NotFoundEntity("404",message.getValue("000"));
		copiaRepository.deleteById(requestDeleteCopiaDTO.getId());
		
	}

	@Override
	public Copia findCopiaByCodigo(@NotEmpty String codigo) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por id", Copia.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Copia copia= copiaRepository.findByCodigo(codigo).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		
		return copia;
	}

}
