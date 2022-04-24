package es.seresco.libreriaspring.controller;

import java.util.List;

import javax.annotation.Resource;

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.dto.copia.*;
import es.seresco.libreriaspring.exceptions.DataInconsistentException;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.seresco.libreriaspring.service.CopiaService;


@RestController
@RequestMapping(path = "/api/Copia")
public class CopiaController {

	@Resource
	private  CopiaService copiaService;
	
	@Resource
	private MessageServiceImpl message;
	
	@GetMapping(path = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCopiaDTO> findByCodigo(@PathVariable String codigo) throws NotFoundEntity{
		return  ResponseEntity.status(HttpStatus.OK).body(copiaService.findByCodigo(codigo));
		
	}
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseCopiaDTO>> findAll() throws NotFoundEntity{
		return ResponseEntity.status(HttpStatus.OK).body(copiaService.findAll());
	}
	
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCopiaDTO> createCopia(@RequestBody RequestCreateCopiaDTO requestCreateCopiaDTO) throws NotFoundEntity{
		return ResponseEntity.status(HttpStatus.OK).body(copiaService.create(requestCreateCopiaDTO));
	}
	@PutMapping(path = "/{idCopia}/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateCopia(@PathVariable Long idCopia ,@RequestBody RequestUpdateCopiaDTO  requestUpdateCopiaDTO) throws DataInconsistentException, NotFoundEntity {
		if(!idCopia.equals(requestUpdateCopiaDTO.getId())) {
			throw new DataInconsistentException("400",message.getValue("020"));
		}
		copiaService.update(requestUpdateCopiaDTO);
		return  ResponseEntity.status(HttpStatus.OK).body(null);
		
		
	}
	@DeleteMapping(path = "/{idCopia}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCopia(@PathVariable Long idCopia ,@RequestBody RequestDeleteCopiaDTO  requestDeleteCopiaDTO) throws DataInconsistentException, NotFoundEntity {
		if(!idCopia.equals(requestDeleteCopiaDTO.getId())) {
			throw new DataInconsistentException("400",message.getValue("020"));
		}
		if(!requestDeleteCopiaDTO.getFlag()) {
			throw new DataInconsistentException("400",message.getValue("021"));
		}
		copiaService.delete(requestDeleteCopiaDTO);
		return  ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
}
