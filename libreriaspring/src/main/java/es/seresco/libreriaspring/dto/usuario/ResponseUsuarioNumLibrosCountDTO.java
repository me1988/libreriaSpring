package es.seresco.libreriaspring.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter


public class ResponseUsuarioNumLibrosCountDTO extends UsuarioModelDTO{

	private Long count;
	public ResponseUsuarioNumLibrosCountDTO(String codigo, String nombre, String apellidos, Long count) {
		super();
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.count=count;
	}
	public Long getCount() {
		return count;
	}


	
}
