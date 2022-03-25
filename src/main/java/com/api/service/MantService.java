package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.entity.Mant;

public interface MantService {

	public Mant save(Mant entity);

	public List<Mant> list();

	public Optional<Mant> findById(Long id);
	
	public void delete(Long id);

}
