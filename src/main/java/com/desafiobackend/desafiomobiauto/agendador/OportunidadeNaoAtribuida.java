package com.desafiobackend.desafiomobiauto.agendador;

import com.desafiobackend.desafiomobiauto.model.repository.OportunidadeRepository;
import com.desafiobackend.desafiomobiauto.service.OportunidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class OportunidadeNaoAtribuida {

    @Autowired
    private OportunidadeRepository oportunidadeRepository;
    @Autowired
    private OportunidadeService oportunidadeService;

    private final Logger logger = LoggerFactory.getLogger(OportunidadeNaoAtribuida.class);

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao() {

        oportunidadeRepository.findAllByAtribuidaIsFalse().forEach(oportunidade -> {
            try {
                oportunidadeService.distribuirOportunidade(oportunidade);
            } catch (Exception exception) {
                logger.error("Erro ao atribuir oportunidade: {}", exception.getMessage());
            }
        });
    }

}
