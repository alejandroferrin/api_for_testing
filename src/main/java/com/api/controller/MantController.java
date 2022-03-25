package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.entity.Mant;
import com.api.service.MantService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/mantenimiento")
public class MantController {

	@Autowired
	MantService service;

	@ApiOperation("Obtiene el objeto con el id indicado")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Mant result = service.findById(id).orElse(null);
		if (result == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(result);
	}

	@ApiOperation("Obtiene todos los objetos de la DB")
	@GetMapping("")
	public ResponseEntity<?> getByAll() {
		List<Mant> result = service.list();
		if (result.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(result);
	}

	@ApiOperation("Borra el objeto con el id indicado")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@ApiOperation("Guarda una entidad en la DB")
	@PostMapping("")
	public ResponseEntity<?> newCalendario(@RequestBody Mant entity) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.save(entity));
	}

	@ApiOperation("Edita un objeto")
	@PutMapping("/{id}")
	public ResponseEntity<?> editCalendario(@RequestBody Mant editar,
			@PathVariable Long id) {

		return service.findById(id).map(x -> {

			x.setTipo(editar.getTipo());
			x.setZona(editar.getZona());

			return ResponseEntity.ok(service.save(x));

		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});

	}

}
