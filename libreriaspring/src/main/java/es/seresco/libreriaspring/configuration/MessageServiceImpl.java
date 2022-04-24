package es.seresco.libreriaspring.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;


//como pasarle el idioma al metodo,con campo de archivo properties??
@Service
public class MessageServiceImpl {

	
	@Autowired
	private MessageSource messageSource;

	
	
	
	public String getValue(String key) {
		return messageSource.getMessage(key, null, new Locale("es"));
	}

	
	
	public String getValue(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

	
	
	public String getValueWithParams(String key, Locale locale, Object... params) {
		return messageSource.getMessage(key, params, locale);
	}
	
	
	public String getValueWithParams(String key, Object... params) {
		return messageSource.getMessage(key, params,null);
	}

}
	

	
