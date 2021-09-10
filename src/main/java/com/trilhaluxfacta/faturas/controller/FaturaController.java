package com.trilhaluxfacta.faturas.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.trilhaluxfacta.faturas.domain.Fatura;
import com.trilhaluxfacta.faturas.dto.FaturaDTO;
import com.trilhaluxfacta.faturas.dto.FaturaNewDTO;
import com.trilhaluxfacta.faturas.service.FaturaService;

@RestController
@RequestMapping(value = "/faturas")
public class FaturaController {
	
	@Autowired
	private FaturaService service;
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<FaturaDTO> findFatura(@PathVariable Integer id) {
		Fatura obj = service.find(id);
		return ResponseEntity.ok().body(new FaturaDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<FaturaDTO>> findAllFatura(){
		List<Fatura> list = service.findAll();
		List<FaturaDTO> listDto = list
									.stream()
									.map(x -> new FaturaDTO(x))
									.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> addFatura(@RequestBody FaturaNewDTO objDto){
		Fatura obj = service.fromNewDTO(objDto);
		obj = service.addFatura(obj);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("{/id}")
					.buildAndExpand(obj.getIdCliente())
					.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<Void> updateSomeParams(@RequestBody FaturaDTO objDto,
												 @PathVariable Integer id) {
		Fatura obj = service.fromDTO(objDto);
		obj.setIdCliente(id);
		obj = service.updateSomeParams(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
