package com.daomysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;


import com.entity.Word;
import com.repository.WordRepository;

public class WordDAOMysql implements WordRepository{
	
	private DataSourceJDBC mySqlDataSource = new DataSourceJDBC();
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(mySqlDataSource.getDataSource());
    
    public WordDAOMysql() throws SQLException {
        this.mySqlDataSource = mySqlDataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

	

	@Override
	public List<Word> findAll() {
		 List<Map<String, Object>> rows = (List<Map<String, Object>>)
				 jdbcTemplate.queryForList("SELECT * FROM word");
	        rows.forEach(System.out::println);
		 List<Word> wordList = new ArrayList<Word>();
		 for (Map row : rows) {
			 Word w = new Word(); 
			 w.setIdWord((long) row.get("idWord"));
			 w.setWord((String) row.get("word"));
			 wordList.add(w);
		 }
		 return wordList;
	}



	@Override
	public List<Word> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Word> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Word> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public <S extends Word> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteInBatch(Iterable<Word> entities) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Word getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Word> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Word> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Page<Word> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Word> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Optional<Word> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(Word entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteAll(Iterable<? extends Word> entities) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public <S extends Word> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Word> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Word> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public <S extends Word> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}
