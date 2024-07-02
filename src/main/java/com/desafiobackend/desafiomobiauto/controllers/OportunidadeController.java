package com.desafiobackend.desafiomobiauto.controllers;

import com.desafiobackend.desafiomobiauto.application.dtos.OportunidadeDTO;
import com.desafiobackend.desafiomobiauto.model.entity.Oportunidade;
import com.desafiobackend.desafiomobiauto.service.OportunidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

    @Autowired
    OportunidadeService oportunidadeService;

    @PostMapping
    public ResponseEntity<Oportunidade> criarOportunidade(@RequestBody @Validated OportunidadeDTO oportunidadeDTO) throws Exception {
        Oportunidade novaOportunidade = oportunidadeService.criarOportunidade(oportunidadeDTO);
        return new ResponseEntity<>(novaOportunidade, HttpStatus.CREATED);
    }

    @PostMapping("/atribuir")
    public ResponseEntity<Oportunidade> atribuirOportunidade(@RequestBody @Validated OportunidadeDTO oportunidadeDTO) throws Exception {
        Oportunidade novaOportunidade = oportunidadeService.atribuirOportunidadeAoAssistente(oportunidadeDTO);
        return new ResponseEntity<>(novaOportunidade, HttpStatus.CREATED);
    }

    @PostMapping("/transferir")
    public ResponseEntity<Oportunidade> transferirOportunidade(@RequestBody @Validated OportunidadeDTO transferirDTO) throws Exception {
        Oportunidade oportunidade = oportunidadeService.transferirOportunidade(transferirDTO);
        return ResponseEntity.ok(oportunidade);
    }

    @PostMapping("/editar")
    public ResponseEntity<Oportunidade> editarOportunidade(@RequestBody @Validated OportunidadeDTO oportunidadeDTO) throws Exception {
        Oportunidade oportunidade = oportunidadeService.editarOportunidade(oportunidadeDTO.getId(), oportunidadeDTO);
        return ResponseEntity.ok(oportunidade);
    }


    @GetMapping
    public ResponseEntity<List<Oportunidade>> getAllOpotunidades(){
        List<Oportunidade> oportunidades = this.oportunidadeService.getAllOportunidades();
        return new ResponseEntity<>(oportunidades, HttpStatus.OK);
    }
}
