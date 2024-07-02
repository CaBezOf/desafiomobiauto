package com.desafiobackend.desafiomobiauto.model.repository;

import com.desafiobackend.desafiomobiauto.model.entity.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OportunidadeRepository  extends JpaRepository<Oportunidade, Long> {

    Optional<Oportunidade> findOportunidadeById(Long id);

    List<Oportunidade> findAllByAtribuidaIsFalse();

}
