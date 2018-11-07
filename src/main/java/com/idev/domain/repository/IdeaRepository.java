package com.idev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idev.domain.model.Idea;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {

}
