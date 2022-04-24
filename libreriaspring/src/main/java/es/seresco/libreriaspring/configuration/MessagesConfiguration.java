package es.seresco.libreriaspring.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessagesConfiguration {
	
	
	@Bean
	public MessageSource messageSource () {
		
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages/exception");
		source.setFallbackToSystemLocale(false);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	} 
	
	
}

