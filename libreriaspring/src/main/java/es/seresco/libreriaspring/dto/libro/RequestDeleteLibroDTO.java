package es.seresco.libreriaspring.dto.libro;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDeleteLibroDTO  {

	@NotNull
	private Long id;

	@AssertTrue
	private Boolean flag;
}
