package es.seresco.libreriaspring.dto.copia;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestUpdateCopiaDTO extends ModelCopiaDTO {

	@NotNull
	private Long id;
}
