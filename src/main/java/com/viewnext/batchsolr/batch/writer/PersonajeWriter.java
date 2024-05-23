package com.viewnext.batchsolr.batch.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Clase que guarda los distintos writers para este batch
 */
@Configuration
@Component
public class PersonajeWriter {
	
	private static final Logger log = LoggerFactory.getLogger(PersonajeWriter.class);
	
	/**
	 * El writer para escribir en el csv
	 * 
	 * @return El writer que escribirá en el csv
	 */
	@Bean(value = "writerSolr")
	public PersonajeItemWriter writerLocal() {
		log.info("Escribiendo items...");
		
		return new PersonajeItemWriter();
	}
}
