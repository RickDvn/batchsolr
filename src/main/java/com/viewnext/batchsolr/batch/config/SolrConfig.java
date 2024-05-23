package com.viewnext.batchsolr.batch.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpJdkSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Bean("collectionPersonaje")
    public SolrClient solrClient() {
    	return new HttpJdkSolrClient.Builder("http://localhost:9777/solr/personajes").build();
    }
}
