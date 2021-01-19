package com.chrisjhkim.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	public List<MyData> getAll(){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		
		List<MyData> list = null;
		query.select(root);
		list = (List<MyData>)entityManager
				.createQuery(query)
				.getResultList();
		return list;
	}
	
	
	
	
	@Override
	public List<MyData> getAll_JPQL() {
		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
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


	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> find_JPQL(String fstr) {
		List<MyData> list = null;
		String qstr = "from MyData where id = :fid or name like :fname or mail like :fmail";
		//String qstr = "from MyData where id = ?1 or name like ?2 or mail like ?3";
		Long fid = 0L;
		try {
			fid = Long.parseLong(fstr);
		}catch (NumberFormatException e ) {
			//e.printStackTrace();
			System.out.println("fstr not number");
		}
		Query query = entityManager.createQuery(qstr)
				.setParameter("fid", fid)
				.setParameter("fname",  "%"+fstr+"%")
				.setParameter("fmail",  fstr+"@%");
//				.setParameter(1, fid)
//				.setParameter(2, "%"+fstr+"%")
//				.setParameter(3, fstr+"@%");
		
		
		
		list = query.getResultList();
		return list;
	}
	
	

}
