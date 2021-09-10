package com.trilhaluxfacta.faturas.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trilhaluxfacta.faturas.domain.Fatura;

public class FaturaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer idCliente;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date dataComp;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date dataPag;
	private Double valor;
	
	public FaturaDTO() {
	}
	
	public FaturaDTO(Fatura obj) {
		idCliente = obj.getIdCliente();
		dataComp = obj.getDataComp();
		dataPag = obj.getDatePag();
		valor = obj.getValor();
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataComp() {
		return dataComp;
	}

	public void setDataComp(Date dataComp) {
		this.dataComp = dataComp;
	}

	public Date getDataPag() {
		return dataPag;
	}

	public void setDataPag(Date dataPag) {
		this.dataPag = dataPag;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
