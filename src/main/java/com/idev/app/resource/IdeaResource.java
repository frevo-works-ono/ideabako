package com.idev.app.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idev.domain.model.Idea;
import com.idev.domain.service.IdeaService;

@RestController
@RequestMapping("api/ideas")
public class IdeaResource {
	@Autowired
	private IdeaService ideaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Idea> findAll(){
		return ideaService.findAll();
	}
	
	@RequestMapping(value = "{id}",method = RequestMethod.PUT)
	public Idea update(@PathVariable long id,@RequestBody Idea input) {
		Idea entity = ideaService.find(id);
		if(entity == null) {
			throw new NotFoundException();
		}
		
		BeanUtils.copyProperties(input, entity);
		
		return ideaService.save(entity);
	}
}
