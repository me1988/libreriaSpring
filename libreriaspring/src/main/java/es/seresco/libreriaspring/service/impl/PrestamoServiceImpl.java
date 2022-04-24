package es.seresco.libreriaspring.service.impl;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.dto.prestamo.RequestCreatePrestamoDTO;
import es.seresco.libreriaspring.dto.prestamo.RequestUpdatePrestamoDTO;
import es.seresco.libreriaspring.dto.prestamo.ResponsePrestamoDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.exceptions.NumPrestamosException;
import es.seresco.libreriaspring.exceptions.ProyectExceptions;
import es.seresco.libreriaspring.mapper.PrestamoMapper;
import es.seresco.libreriaspring.model.Copia;
import es.seresco.libreriaspring.model.Prestamo;
import es.seresco.libreriaspring.model.Usuario;
import es.seresco.libreriaspring.repository.PrestamoRespository;
import es.seresco.libreriaspring.service.CopiaService;
import es.seresco.libreriaspring.service.LibroService;
import es.seresco.libreriaspring.service.PrestamoService;
import es.seresco.libreriaspring.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Service(PrestamoService.BEAN_NAME)
@Slf4j
@Validated
@Transactional(rollbackFor =ProyectExceptions.class)
public class PrestamoServiceImpl implements PrestamoService{
	
	
	@Autowired
	private MessageServiceImpl message;
	
	@Autowired
	private PrestamoRespository prestamoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@Autowired
	private CopiaService copiaService;
	
	@Autowired
	private PrestamoMapper mapper;
	
	@Autowired
	private Environment environment;
	
	
	@Override
	public ResponsePrestamoDTO obtenerPrestamoLibro(@Valid RequestCreatePrestamoDTO RequestCreatePrestamoDTO) throws NotFoundEntity, NumPrestamosException {
		log.info("Creando registro de la entidad {} en el bean {}", Prestamo.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Usuario usuario = usuarioService.findUsuarioByCodigo(RequestCreatePrestamoDTO.getUsuarioId());
		Copia copia=copiaService.findCopiaByCodigo(RequestCreatePrestamoDTO.getCopiaId());
		
		Prestamo prestamo = new Prestamo();
		prestamo.setFechaInicio(new Date());
		prestamo.setUsuario(usuario);
		prestamo.setCopia(copia);
		if(prestamoRepository.countPrestamos(usuario.getId())<=Integer.parseInt(environment.getProperty("key.max.prestamos"))){
		
			throw new NumPrestamosException("404",message.getValue("030"));
		}
		
		prestamoRepository.save(prestamo);
		
		return mapper.mapEntityToDTO(prestamo);
	}

	@Override
	public void devolverPrestamo(@Valid RequestUpdatePrestamoDTO requestUpdatePrestamoDTO) throws NotFoundEntity {
		log.info("Borrando registro de la entidad {} en el bean {}", Usuario.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Prestamo prestamo=prestamoRepository.prestamoExistByUsuarioAndCopia(requestUpdatePrestamoDTO.getUsuarioId(), requestUpdatePrestamoDTO.getCopiaId())
				.orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		prestamo.setFechaFin(new Date());	
		
		
		prestamoRepository.save(prestamo);
		
	}

}
