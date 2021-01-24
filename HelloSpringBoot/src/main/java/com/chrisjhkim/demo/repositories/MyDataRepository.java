package com.chrisjhkim.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chrisjhkim.demo.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long>{
	//public Optional<MyData> findByIdIn(Long id);

	//WARNING Parameter type (Long) does not match domain class property definition (long).
	public Optional<MyData> findById(long id);
	
	
	public MyData findByName(String name);
	public Optional<MyData> findByNameLike(String name);
	public Optional<List<MyData>> findByNameContaining(String name);
	public Optional<MyData> findByNameIsLike(String name);
	public MyData findByNameOrMemo(String name, String memo);
	//ERROR The return type is incompatible with CrudRepository<MyData,Long>.findById(Long)
	//public MyData findById(Long name);

	@Query("SELECT d FROM MyData d ORDER BY d.name")		// d는 Mydata의 alias 
	List<MyData> findAllOrderByName();
	
	//public Optional<MyData> findOne(Long id);		// 안됨 왜지 
}
