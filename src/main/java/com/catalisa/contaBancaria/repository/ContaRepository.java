package com.catalisa.contaBancaria.repository;

import com.catalisa.contaBancaria.model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, Long> {
}
