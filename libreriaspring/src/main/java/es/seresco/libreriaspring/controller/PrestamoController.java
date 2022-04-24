package es.seresco.libreriaspring.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.seresco.libreriaspring.exceptions.*;
import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.dto.prestamo.RequestCreatePrestamoDTO;
import es.seresco.libreriaspring.dto.prestamo.RequestUpdatePrestamoDTO;
import es.seresco.libreriaspring.dto.prestamo.ResponsePrestamoDTO;
import es.seresco.libreriaspring.service.PrestamoService;

@RestController
@RequestMapping(path = "/api/Prestamo")
public class PrestamoController {

	@Resource
	private PrestamoService prestamoService;
	
	@Resource
	private MessageServiceImpl message;
	
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponsePrestamoDTO> createCopia(@RequestBody RequestCreatePrestamoDTO requestCreatePrestamoDTO) throws NotFoundEntity, NumPrestamosException{
		return ResponseEntity.status(HttpStatus.OK).body(prestamoService.obtenerPrestamoLibro(requestCreatePrestamoDTO));
	}
	@PutMapping(path = "/{idCopia}/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateCopia(@PathVariable Long idCopia ,@RequestBody RequestUpdatePrestamoDTO requestUpdatePrestamoDTO) throws DataInconsistentException, NotFoundEntity {
		if(!idCopia.equals(requestUpdatePrestamoDTO.getId())) {
			throw new DataInconsistentException("400",message.getValue("020"));
		}
		prestamoService.devolverPrestamo(requestUpdatePrestamoDTO);
		return  ResponseEntity.status(HttpStatus.OK).body(null);
		
		
	}
}
