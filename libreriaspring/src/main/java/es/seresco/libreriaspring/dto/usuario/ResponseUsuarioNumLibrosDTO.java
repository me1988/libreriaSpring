package es.seresco.libreriaspring.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ResponseUsuarioNumLibrosDTO extends UsuarioModelDTO{

	
	
	public ResponseUsuarioNumLibrosDTO(String codigo, String nombre, String apellidos) {
		super();
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		
		
	}

	
	
}
