package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Mant;
import com.api.repo.MantRepository;

@Service
public class MantServiceImpl implements MantService {

	@Autowired
	MantRepository repo;

	@Override
	public Mant save(Mant entity) {
		return repo.save(entity);
	}

	@Override
	public List<Mant> list() {
		return repo.findAll();
	}

	@Override
	public Optional<Mant> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);

	}

}
