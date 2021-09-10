package com.trilhaluxfacta.faturas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Fatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Integer idCliente;
	
	@Column(name = "dataComp")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date dataComp;
	
	@Column(name = "dataPag")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date datePag;
	
	@Column(name = "valor")
	private Double valor;
	
}
