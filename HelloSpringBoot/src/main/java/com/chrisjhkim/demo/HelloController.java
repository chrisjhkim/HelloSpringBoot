package com.chrisjhkim.demo;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chrisjhkim.demo.repositories.MyDataRepository;

@Controller
public class HelloController {
	@Autowired
	MyDataRepository repository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	//@Autowired
	MyDataDaoImp dao;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData myData
			,ModelAndView mav) {
		System.out.println("/ GET " + myData);
		mav.setViewName("index");
		mav.addObject("formModel", myData);
//		Iterable<MyData> list = repository.findAll();
		System.out.println(dao==null ? "dao null " : "dao not null " );
		System.out.println(dao.getClass());
		Iterable<MyData> list = dao.getAll();

		mav.addObject("datalist", list);
		System.out.println();
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(
			@ModelAttribute("formModel")
			@Validated MyData myData
			,BindingResult result
			,ModelAndView mav) {
		System.out.println("/ POST ");
		System.out.println("ModelAttribute = "+myData);
		
		ModelAndView res = null;
		if( !result.hasErrors() ) {
			System.out.println("result="+result);
			MyData saveResultData = repository.saveAndFlush(myData);
			System.out.println("save result = " + saveResultData);
//			repository.save(myData);
			res = new ModelAndView("redirect:/");
		}else {
			System.out.println("-ERRORS");
			int index = 0 ;
			
			for ( ObjectError objError : result.getAllErrors() ) {
				System.out.println( "-["+(++index)+"]"+ objError.toString());
				for ( int i = 0 ; i < objError.getArguments().length ; i ++ ) {
					System.out.println("--- "+ objError.getArguments()[i]);
				}
				System.out.println(objError.getCode());
				System.out.println(objError.getDefaultMessage());
				System.out.println(objError.getObjectName());
				System.out.println(objError.getCodes());
				System.out.println();
			}
			mav.setViewName("index");
			mav.addObject("msg", "sorry, error is occured...");
			Iterable<MyData> list = repository.findAll();
			mav.addObject("datalist", list);
			res = mav;
		}
		System.out.println();
		return res;
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		System.out.println("/find - GET");
		mav.setViewName("find");
		mav.addObject("title"	,"Find Page");
		mav.addObject("msg"		,"MyData의 예제입니다");
		mav.addObject("value"	,"");
		Iterable<MyData> list = dao.getAll();
		mav.addObject("datalist", list);
		System.out.println();
		return mav;
	}
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public ModelAndView find(ModelAndView mav, HttpServletRequest req) {
		System.out.println("/find - POST");
		mav.setViewName("find");
		String param = req.getParameter("fstr");
		if( param == null ) {
			mav = new ModelAndView("redirect:/find"); 
		} else {
			mav.addObject("title"	,"Find Result");
			mav.addObject("msg"		,param+"의 검색결과");
			mav.addObject("value"	,param);
			List<MyData> list = dao.find(param);
			System.out.println(list);
			mav.addObject("datalist", list);
		}
		System.out.println();
		return mav;
	}




	

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(
			@ModelAttribute MyData myData
			,@PathVariable int id
			,ModelAndView mav ) {
		System.out.println("/edit GET");
		mav.setViewName("edit");
		mav.addObject("title", "edit myData.");
		Optional<MyData> data = repository.findById((long)id);
		if( data != null ) {
			System.out.println("data = " + data.get());
		}
		mav.addObject("formModel", data.get());
		System.out.println();
		return mav;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(
			@ModelAttribute MyData myData
			,ModelAndView mav) {
		System.out.println("/edit POST");
		System.out.println(myData);
		repository.saveAndFlush(myData);
		return new ModelAndView("redirect:/");
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView delete(
			@PathVariable int id
			,ModelAndView mav ) {
		System.out.println("/delete/{id} GET");
		System.out.println("id="+id);
		mav.setViewName("delete");
		mav.addObject("title", "delete myData");
		Optional<MyData> data = repository.findById(id);
		if ( data != null && data.get()!= null )
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam long id
			,ModelAndView mav) {
		System.out.println("/delete POST id=" +id );
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView search(
			@ModelAttribute MyData myData
			,ModelAndView mav) {
		System.out.println("/search GET" + myData);
		System.out.println(repository.findByName(myData.getName())==null?"":repository.findByName(myData.getName()));
		Optional<List<MyData>> searchResult = repository.findByNameContaining(myData.getName());

		if( searchResult.isPresent()) {
			System.out.println("searchResult");
			for( MyData data : searchResult.get()) {
				System.out.println(data);
			}
			
			System.out.println();
		}else
			System.out.println("searchResult is Empty");
		System.out.println();
		
		mav.setViewName("redirect:/");
		return mav;
	}
	
	
	
	@RequestMapping(value="/backup", method=RequestMethod.POST)
	public ModelAndView send_1(
			@RequestParam String text1
			,ModelAndView mav) {
		System.out.println("index - POST");
		mav.setViewName("index");
		mav.addObject("val", "your input=" + text1 );
		return mav;
	}
	
	
	@RequestMapping("/thymeleaf")
	public ModelAndView thymeleaf(ModelAndView mav ) {
		mav.setViewName("index_thymeleaf.html");
		
		return mav;
	}

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct in Controller");
		dao = new MyDataDaoImp(entityManager);
	}
}
