package es.seresco.libreriaspring.service.impl;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.exceptions.ProyectExceptions;
import es.seresco.libreriaspring.model.Categoria;
import es.seresco.libreriaspring.model.Libro;
import es.seresco.libreriaspring.repository.CategoriaRespository;
import es.seresco.libreriaspring.service.CategoriaService;
import es.seresco.libreriaspring.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Service(CategoriaService.BEAN_NAME)
@Validated
@Transactional(rollbackFor =ProyectExceptions.class)
@Slf4j
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRespository categoriaRespository;
	
	@Autowired
	private MessageServiceImpl message;
	
	@Override
	public Categoria findById(@NotNull Long id) throws NotFoundEntity {
		
		log.info("Buscando campo de entidad {} en el bean {} por id", Libro.class.getSimpleName(),LibroService.BEAN_NAME );
		
		Categoria categoria= categoriaRespository.findById(id).orElseThrow(()-> new NotFoundEntity("404",message.getValue("000")));
		
		return categoria;
		
	}

}
