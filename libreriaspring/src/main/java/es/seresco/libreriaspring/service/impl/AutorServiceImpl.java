package es.seresco.libreriaspring.service.impl;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.exceptions.ProyectExceptions;
import es.seresco.libreriaspring.mapper.AutorMapper;
import es.seresco.libreriaspring.model.Autor;
import es.seresco.libreriaspring.model.Libro;
import es.seresco.libreriaspring.repository.AutorRepository;
import es.seresco.libreriaspring.service.AutorService;
import es.seresco.libreriaspring.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Service(AutorService.BEAN_NAME)
@Slf4j
@Validated
@Transactional(rollbackFor =ProyectExceptions.class)
public class AutorServiceImpl implements AutorService{
	
	@Autowired 
	private AutorRepository autorRepository;

	@Autowired
	private MessageServiceImpl message;


	
	@Override
	public Autor findById(@NotNull Long id) throws NotFoundEntity {
		
		log.info("Buscando campo de entidad {} en el bean {} por id", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Autor autor= autorRepository.findById(id).orElseThrow(()->
			new NotFoundEntity("404",message.getValue("000")));
		
		return autor;
	}

}
