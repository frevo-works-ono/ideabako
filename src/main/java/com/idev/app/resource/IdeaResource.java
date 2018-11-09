package com.idev.app.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idev.domain.model.Idea;
import com.idev.domain.service.IdeaService;
import com.idev.exception.ResourceException;

@RestController
@RequestMapping("api/ideas")
@CrossOrigin
public class IdeaResource {
	@Autowired
	private IdeaService ideaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Idea> findAll(){
		return ideaService.findAll();
	}
	
	@RequestMapping(value = "{id}",method = RequestMethod.GET)
	public Idea find(@PathVariable long id) {
		return ideaService.find(id);
	}
	
	@RequestMapping(value = "{id}",method = RequestMethod.PUT)
	public Idea update(@PathVariable long id,@RequestBody Idea input) {
		Idea entity = ideaService.find(id);
		if(entity == null) {
			throw new ResourceException(HttpStatus.BAD_REQUEST);
		}
		
		BeanUtils.copyProperties(input, entity);
		
		return ideaService.save(entity);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Idea create(@RequestBody Idea input) {
		
		Idea entity = new Idea();
		
		BeanUtils.copyProperties(input, entity);
		
		return ideaService.save(entity);
	}
	
	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		ideaService.delete(id);
	}
}
