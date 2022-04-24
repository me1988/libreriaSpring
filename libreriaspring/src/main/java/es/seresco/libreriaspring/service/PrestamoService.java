package es.seresco.libreriaspring.service;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import es.seresco.libreriaspring.dto.prestamo.RequestCreatePrestamoDTO;
import es.seresco.libreriaspring.dto.prestamo.RequestUpdatePrestamoDTO;
import es.seresco.libreriaspring.dto.prestamo.ResponsePrestamoDTO;
import es.seresco.libreriaspring.exceptions.NotFoundEntity;
import es.seresco.libreriaspring.exceptions.NumPrestamosException;

@Validated
public interface PrestamoService {

	final static String BEAN_NAME="prestamoService";
	
	ResponsePrestamoDTO obtenerPrestamoLibro(@Valid RequestCreatePrestamoDTO RequestCreatePrestamoDTO) throws NotFoundEntity, NumPrestamosException;
	
	void devolverPrestamo(@Valid RequestUpdatePrestamoDTO requestUpdatePrestamoDTO) throws NotFoundEntity;
}
