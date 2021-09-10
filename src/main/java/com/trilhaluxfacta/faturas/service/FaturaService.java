package com.trilhaluxfacta.faturas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.trilhaluxfacta.faturas.domain.Fatura;
import com.trilhaluxfacta.faturas.dto.FaturaDTO;
import com.trilhaluxfacta.faturas.dto.FaturaNewDTO;
import com.trilhaluxfacta.faturas.repository.FaturaRepository;
import com.trilhaluxfacta.faturas.service.exception.DataIntegrityException;
import com.trilhaluxfacta.faturas.service.exception.ObjectNotFoundException;

@Service
public class FaturaService {

	@Autowired
	private FaturaRepository repo;
	
	public Fatura find(Integer id) {
		return repo.findById(id).orElseThrow( 
				() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id:" + 
						id + ", Tipo: " + 
						Fatura.class.getName()));
	}
	
	public List<Fatura> findAll() {
		return repo.findAll();
	}
	
	public Fatura addFatura(Fatura obj) {
		obj.setIdCliente(null);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir a fatura");
		}
	}
	
	public Fatura updateSomeParams(Fatura obj) {
		Fatura newObj = repo.findById(obj.getIdCliente()).orElseThrow( 
				() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id:" + obj.getIdCliente() + ", Tipo: " + 
						Fatura.class.getName()));
		updateDataSomeParams(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateDataSomeParams(Fatura newObj, Fatura obj) {
		if(obj.getDataComp() != null ) {
			newObj.setDataComp(obj.getDataComp());
		}
		if(obj.getDatePag() != null) {
			newObj.setDatePag(obj.getDatePag());
		}
		if(obj.getValor() != null) {
			newObj.setValor(obj.getValor());
		}
	}
	
	public Fatura fromNewDTO(FaturaNewDTO objDto) {
		return new Fatura(objDto.getIdCliente(),
						  objDto.getDataComp(),
						  objDto.getDataPag(),
						  objDto.getValor());
	}
	
	public Fatura fromDTO(FaturaDTO objDto) {
		return new Fatura(null,
						  objDto.getDataComp(),
						  objDto.getDataPag(),
						  objDto.getValor());
	}
	
}
