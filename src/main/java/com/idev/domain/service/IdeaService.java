package com.idev.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idev.domain.model.Idea;
import com.idev.domain.repository.IdeaRepository;

@Service
@Transactional
public class IdeaService {
	
	@Autowired
	private IdeaRepository ideaRepository;
	
	public List<Idea> findAll(){
		return ideaRepository.findAll();
	}
	
	public Idea find(long id) {
		return ideaRepository.findById(id).orElse(null);
	}
	
	public Idea save(Idea entity) {
		return ideaRepository.save(entity);
	}
}
