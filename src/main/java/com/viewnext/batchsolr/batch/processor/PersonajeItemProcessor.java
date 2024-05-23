package com.viewnext.batchsolr.batch.processor;

import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.viewnext.batchsolr.model.Personaje;

/**
 * Precessor que transforma los Personajes a SolrInputDocuments
 */
public class PersonajeItemProcessor implements ItemProcessor<Personaje, SolrInputDocument>{

	private static final Logger log = LoggerFactory.getLogger(PersonajeItemProcessor.class);
	
	@Override
	public SolrInputDocument process(Personaje item) throws Exception {
		log.info("Processing item...");
		
		SolrInputDocument documento = new SolrInputDocument();
		documento.addField("id", item.getId());
		documento.addField("nombre", item.getNombre());
		documento.addField("vida", item.getVida());
		documento.addField("movimiento", item.getMovimiento());
		documento.addField("armadura", item.getArmadura());
		
		return documento;
	}

}
