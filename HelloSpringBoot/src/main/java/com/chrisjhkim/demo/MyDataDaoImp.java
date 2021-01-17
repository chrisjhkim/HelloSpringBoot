package com.chrisjhkim.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImp implements MyDataDao<MyData> {
	private static final long serialVersionUID = 1L;
	
	//@PersistenceContext
	private EntityManager entityManager;

	public MyDataDaoImp() {
		super();
	}
	

	public MyDataDaoImp(EntityManager entityManager) {
		super();
		System.out.println("MyDataDaoImp create");
		this.entityManager = entityManager;
	}


	@Override
	public List<MyData> getAll() {
		System.out.println("getAll 1");
		Query query = entityManager.createQuery("from MyData");
		System.out.println("getAll 2");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		System.out.println("getAll 3");
		entityManager.close();
		return list;
	}


	@Override
	public MyData findById(long id) {
		return (MyData)entityManager
				.createQuery("from MyData where id  = " +id)
				.getSingleResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> findByName(String name) {
		return (List<MyData>)entityManager
				.createQuery("from MyData where name = " +name)
				.getResultList();
	}


	@Override
	public List<MyData> find(String fstr) {
		List<MyData> list = null;
		String qstr = "from MyData where id = :fid or name like :fname or mail like :fmail";
		Long fid = 0L;
		try {
			fid = Long.parseLong(fstr);
		}catch (NumberFormatException e ) {
			e.printStackTrace();
		}
		Query query = entityManager.createQuery(qstr)
				.setParameter("fid", fid)
				.setParameter("fname",  "%"+fstr+"%")
				.setParameter("fmail",  fstr+"@%");
		list = query.getResultList();
		return list;
	}
	
	

}
