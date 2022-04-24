package es.seresco.libreriaspring.dto.copia;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDeleteCopiaDTO {

	@NotNull
	private Long id;

	@AssertTrue
	private Boolean flag;
}
