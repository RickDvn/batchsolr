package com.viewnext.batchsolr.batch.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.viewnext.batchsolr.model.Personaje;

/**
 * Clase que se encarga los distintos reader del batch
 */
@Configuration
@Component
public class PersonajeReader {
	
	private static final Logger log = LoggerFactory.getLogger(PersonajeReader.class);
	
	/**
	 * Reader que lee de la base de datos
	 * 
	 * @return RepositoryItemReader<TarifaPL> con las tarifas de la base de datos
	 */
	@Bean(value = "readerDB")
	public JdbcCursorItemReader<Personaje> read(DataSource dataSource) {
		JdbcCursorItemReader<Personaje> itemReader = new JdbcCursorItemReader<>();
		itemReader.setDataSource(dataSource);
		itemReader.setSql("SELECT id, nombre, vida, movimiento, armadura from personajes");
		itemReader.setRowMapper(createRowMapper());
		
		log.info("Leyendo personajes..");
		return itemReader;
	}
	
	private RowMapper<Personaje> createRowMapper(){
		return new RowMapper<Personaje>() {
			
			@Override
			public Personaje mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Personaje(rs.getLong("id"), rs.getString("nombre"),
						rs.getInt("vida"), rs.getInt("movimiento"), rs.getInt("armadura"));
			}
		};
	}
	
}
