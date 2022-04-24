package es.seresco.libreriaspring.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.dto.libro.RequestCreateLibroDTO;
import es.seresco.libreriaspring.dto.libro.RequestDeleteLibroDTO;
import es.seresco.libreriaspring.dto.libro.RequestUpdateLibroDTO;
import es.seresco.libreriaspring.dto.libro.ResponseLibroDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.exceptions.ProyectExceptions;
import es.seresco.libreriaspring.mapper.LibroMapper;
import es.seresco.libreriaspring.model.Libro;
import es.seresco.libreriaspring.repository.LibroRespository;
import es.seresco.libreriaspring.service.AutorService;
import es.seresco.libreriaspring.service.CategoriaService;
import es.seresco.libreriaspring.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Service(LibroService.BEAN_NAME)
@Slf4j
@Transactional(rollbackFor =ProyectExceptions.class)
@Validated
public class LibroServiceImpl implements LibroService{
	
	@Autowired
	private LibroRespository libroRepository;
	
	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private MessageServiceImpl message;
	
	@Autowired
	private LibroMapper libroMapper;
	
	@Override
	public ResponseLibroDTO findById(@NotNull Long id) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por id", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Libro libro= libroRepository.findById(id).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		ResponseLibroDTO responseLibroDTO=libroMapper.mapEntityToDTO(libro);
		
		responseLibroDTO.setAutorId(libro.getIdAutor().getId());
		responseLibroDTO.setCategoriaId(libro.getIdCategoria().getId());
		
		return responseLibroDTO;
	}

	@Override
	public List<ResponseLibroDTO> findAll() throws NotFoundEntity {
		log.info("Recuperando todos los registros de la entidad {} en el bean {}", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		List<Libro> lista= libroRepository.findAll();
		if(lista.isEmpty()) {
			throw new NotFoundEntity("404",message.getValue("002"));
		}
		return libroMapper.mapListToDTOList(lista);
	}

	@Override
	public ResponseLibroDTO create(@Valid RequestCreateLibroDTO requestCreateLibroDTO) throws NotFoundEntity {
		log.info("Creando registro de la entidad {} en el bean {}", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Libro libro= libroMapper.mapDTOToEntity(requestCreateLibroDTO);
		//find categoria y autor y añadirselos
		libro.setIdAutor(autorService.findById(requestCreateLibroDTO.getAutorId()));
		libro.setIdCategoria(categoriaService.findById(requestCreateLibroDTO.getCategoriaId()));
		
		libro=libroRepository.save(libro);
		
		ResponseLibroDTO responseLibroDTO =libroMapper.mapEntityToDTO(libro);
		
		responseLibroDTO.setAutorId(libro.getIdAutor().getId());
		responseLibroDTO.setCategoriaId(libro.getIdCategoria().getId());
		return responseLibroDTO;
	}

	@Override
	public void update(@Valid RequestUpdateLibroDTO RequestUpdateLibroDTO) throws NotFoundEntity {
		log.info("Actualizando registro de la entidad {} en el bean {}", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Libro libro= libroMapper.mapUpdateDTOToEntity(RequestUpdateLibroDTO);
		if(!libroRepository.existsById(libro.getId()))throw new NotFoundEntity("404",message.getValue("000"));
		
		libro.setIdAutor(autorService.findById(RequestUpdateLibroDTO.getAutorId()));
		libro.setIdCategoria(categoriaService.findById(RequestUpdateLibroDTO.getCategoriaId()));
		
		libroRepository.save(libro);
		
	}

	@Override
	public void delete(@Valid  RequestDeleteLibroDTO RequestDeleteLibroDTO) throws NotFoundEntity {
		log.info("Borrando registro de la entidad {} en el bean {}", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		if(!libroRepository.existsById(RequestDeleteLibroDTO.getId()))throw new NotFoundEntity("404",message.getValue("000"));
		libroRepository.deleteById(RequestDeleteLibroDTO.getId());
	}

	@Override
	public ResponseLibroDTO findByCodigo(@NotEmpty String codigo) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por codigo", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Libro libro= libroRepository.findByCodigo(codigo).orElseThrow(()-> new NotFoundEntity("404",message.getValue("001")));
		
		ResponseLibroDTO responseLibroDTO=libroMapper.mapEntityToDTO(libro);
		
		responseLibroDTO.setAutorId(libro.getIdAutor().getId());
		responseLibroDTO.setCategoriaId(libro.getIdCategoria().getId());
		
		log.info("El libro buscado es {}",responseLibroDTO.toString());
		
		return responseLibroDTO;
	}

	@Override
	public Libro findLibroById(@NotNull Long id) throws NotFoundEntity {
		log.info("Buscando campo de entidad {} en el bean {} por id", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		return libroRepository.findById(id).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
	}

}
