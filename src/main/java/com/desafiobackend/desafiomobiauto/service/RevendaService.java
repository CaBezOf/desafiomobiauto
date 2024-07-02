package com.desafiobackend.desafiomobiauto.service;

import com.desafiobackend.desafiomobiauto.model.entity.Oportunidade;
import com.desafiobackend.desafiomobiauto.model.entity.Revenda;
import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import com.desafiobackend.desafiomobiauto.model.repository.RevendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevendaService {

   @Autowired
   private RevendaRepository revendaRepository;

   public Usuario atribuiUsuarioRevenda (Usuario usuario){
        Revenda revendaExistente = revendaRepository.findByCnpj(usuario.getRevenda().getCnpj());
        if (revendaExistente != null)  usuario.setRevenda(revendaExistente);
        return usuario;
    }

   public Oportunidade atribuiOportunidadeRevenda (Oportunidade oportunidade ){
       Revenda revendaExistente = revendaRepository.findByCnpj(oportunidade.getRevenda().getCnpj());
       if (revendaExistente != null)  oportunidade.setRevenda(revendaExistente);
       return oportunidade;
   }
}
