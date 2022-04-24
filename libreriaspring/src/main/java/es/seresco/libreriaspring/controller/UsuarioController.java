package es.seresco.libreriaspring.controller;




import java.util.List;

import javax.annotation.Resource;

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
import es.seresco.libreriaspring.dto.usuario.RequestCreateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.RequestDeleteUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.RequestUpdateUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosCountDTO;
import es.seresco.libreriaspring.dto.usuario.ResponseUsuarioNumLibrosDTO;
import es.seresco.libreriaspring.exceptions.DataInconsistentException;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.service.UsuarioService;

@RestController
@RequestMapping(path = "/api/usuario")
public class UsuarioController {

	@Resource
	private  UsuarioService usuarioService;
	
	@Resource
	private MessageServiceImpl message;
	
	@GetMapping(path = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseUsuarioDTO> findByCodigo(@PathVariable String codigo) throws NotFoundEntity{
		return  ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByCodigo(codigo));
		
	}
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseUsuarioDTO>> findAll() throws NotFoundEntity{
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}
	
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseUsuarioDTO> createCopia(@RequestBody RequestCreateUsuarioDTO requestCreateUsuarioDTO) throws NotFoundEntity{
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.create(requestCreateUsuarioDTO));
	}
	@PutMapping(path = "/{idUsuario}/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateUsuario(@PathVariable Long idUsuario ,@RequestBody RequestUpdateUsuarioDTO requestUpdateUsuarioDTO) throws DataInconsistentException, NotFoundEntity {
		if(!idUsuario.equals(requestUpdateUsuarioDTO.getId())) {
			throw new DataInconsistentException("400",message.getValue("020"));
		}
		usuarioService.update(requestUpdateUsuarioDTO);
		return  ResponseEntity.status(HttpStatus.OK).body(null);
		
		
	}
	@DeleteMapping(path = "/{idUsuario}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long idUsuario ,@RequestBody RequestDeleteUsuarioDTO  requestDeleteUsuarioDTO) throws DataInconsistentException, NotFoundEntity {
		if(!idUsuario.equals(requestDeleteUsuarioDTO.getId())) {
			throw new DataInconsistentException("400",message.getValue("020"));
		}
		if(!requestDeleteUsuarioDTO.getFlag()) {
			throw new DataInconsistentException("400",message.getValue("021"));
		}
		usuarioService.delete(requestDeleteUsuarioDTO);
		return  ResponseEntity.status(HttpStatus.OK).body(null);
	}
	@GetMapping(path = "/maxLeidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseUsuarioNumLibrosCountDTO>> findUsuarioMasLibros() throws NotFoundEntity{
		return  ResponseEntity.status(HttpStatus.OK).body(usuarioService.findUsuarioMaxLibros());
		
	}
	@GetMapping(path = "/minLeidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseUsuarioNumLibrosCountDTO>> findUsuarioMenosLibros() throws NotFoundEntity{
		return  ResponseEntity.status(HttpStatus.OK).body(usuarioService.findUsuarioMinLibros());
		
	}
}
