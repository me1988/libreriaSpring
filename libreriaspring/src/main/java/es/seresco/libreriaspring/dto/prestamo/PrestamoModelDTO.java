package es.seresco.libreriaspring.dto.prestamo;

import java.util.Date;

import es.seresco.libreriaspring.model.Copia;
import es.seresco.libreriaspring.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoModelDTO {

	private String copiaId;

	private String usuarioId;

}
