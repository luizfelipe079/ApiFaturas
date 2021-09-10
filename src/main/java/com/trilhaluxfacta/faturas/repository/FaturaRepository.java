package com.trilhaluxfacta.faturas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilhaluxfacta.faturas.domain.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura, Integer>{

}
