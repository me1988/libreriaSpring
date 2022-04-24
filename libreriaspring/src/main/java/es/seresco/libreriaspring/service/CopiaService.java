package es.seresco.libreriaspring.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.dto.copia.RequestCreateCopiaDTO;
import es.seresco.libreriaspring.dto.copia.RequestDeleteCopiaDTO;
import es.seresco.libreriaspring.dto.copia.RequestUpdateCopiaDTO;
import es.seresco.libreriaspring.dto.copia.ResponseCopiaDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.model.*;
@Validated
public interface CopiaService {

	
	final static String BEAN_NAME="copiaService";
	
	ResponseCopiaDTO findById(@NotNull Long id) throws NotFoundEntity;
	
	ResponseCopiaDTO findByCodigo(@NotEmpty String codigo) throws NotFoundEntity;
	
	Copia findCopiaByCodigo(@NotEmpty String codigo) throws NotFoundEntity;
	
	
	List<ResponseCopiaDTO> findAll() throws NotFoundEntity;
	
	ResponseCopiaDTO create(@Valid RequestCreateCopiaDTO requestCreateCopiaDTO) throws NotFoundEntity;
	
	void update(@Valid RequestUpdateCopiaDTO  requestUpdateCopiaDTO) throws NotFoundEntity;
	
	void delete(@Valid RequestDeleteCopiaDTO requestDeleteCopiaDTO) throws NotFoundEntity;
}
