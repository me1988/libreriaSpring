package es.seresco.libreriaspring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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

import es.seresco.libreriaspring.configuration.MessageServiceImpl;
import es.seresco.libreriaspring.dto.libro.RequestCreateLibroDTO;
import es.seresco.libreriaspring.dto.libro.RequestDeleteLibroDTO;
import es.seresco.libreriaspring.dto.libro.RequestUpdateLibroDTO;
import es.seresco.libreriaspring.dto.libro.ResponseLibroDTO;
import es.seresco.libreriaspring.exceptions.DataInconsistentException;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.service.LibroService;

@RestController
@RequestMapping(path = "/api/Libro")
public class LibroController {

	@Resource
	private LibroService libroService;
	
	@Resource
	private MessageServiceImpl message;
	
	@GetMapping(path = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseLibroDTO> findByCodigo(@PathVariable String codigo) throws NotFoundEntity{
		return  ResponseEntity.status(HttpStatus.OK).body(libroService.findByCodigo(codigo));
		
	}

	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseLibroDTO>> findAll() throws NotFoundEntity{
		return ResponseEntity.status(HttpStatus.OK).body(libroService.findAll());
	}
	
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseLibroDTO> createLibro(@RequestBody RequestCreateLibroDTO requestCreateLibroDTO) throws NotFoundEntity{
		return ResponseEntity.status(HttpStatus.CREATED).body(libroService.create(requestCreateLibroDTO));
	}
	@PutMapping(path = "/{idLibro}/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateLibro(@PathVariable Long idLibro,@RequestBody RequestUpdateLibroDTO  RequestUpdateLibroDTO) throws DataInconsistentException, NotFoundEntity {
		if(!idLibro.equals(RequestUpdateLibroDTO.getId())) {
			throw new DataInconsistentException("400",message.getValue("020"));
		}
		libroService.update(RequestUpdateLibroDTO);
		return  ResponseEntity.status(HttpStatus.OK).body(null);
	}
	@DeleteMapping(path = "/{idLibro}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteLibro(@PathVariable Long idLibro,@RequestBody RequestDeleteLibroDTO  RequestDeleteLibroDTO) throws DataInconsistentException, NotFoundEntity {
		if(!idLibro.equals(RequestDeleteLibroDTO.getId())) {
			throw new DataInconsistentException("400",message.getValue("020"));
		}
		if(!RequestDeleteLibroDTO.getFlag()) {
			throw new DataInconsistentException("400",message.getValue("021"));
		}
		libroService.delete(RequestDeleteLibroDTO);
		return  ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}

