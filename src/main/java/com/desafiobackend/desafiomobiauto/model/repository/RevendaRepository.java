package com.desafiobackend.desafiomobiauto.model.repository;

import com.desafiobackend.desafiomobiauto.model.entity.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RevendaRepository extends JpaRepository<Revenda, Long> {

    Revenda findByCnpj(String cnpj);

}
