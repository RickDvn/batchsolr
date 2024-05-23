package com.viewnext.batchsolr.batch.writer;

import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonajeItemWriter implements ItemWriter<SolrInputDocument>{

	@Autowired
	private SolrClient solrClient;
	
	@Override
	public void write(Chunk<? extends SolrInputDocument> chunk) throws Exception {
		solrClient.add((Collection<SolrInputDocument>) chunk.getItems());
		solrClient.commit();
	}

}
