package com.viewnext.batchsolr.batch.writer;

import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Este es el writer que se encarga de indexar los personajes en Solr utilizando el SolrClient
 * transformados en SolrInputDocument
 */
public class PersonajeItemWriter implements ItemWriter<SolrInputDocument>{

	@Autowired
	private SolrClient solrClient;
	
	@Override
	public void write(Chunk<? extends SolrInputDocument> chunk) throws Exception {
		solrClient.add((Collection<SolrInputDocument>) chunk.getItems());
		solrClient.commit();
	}

}
