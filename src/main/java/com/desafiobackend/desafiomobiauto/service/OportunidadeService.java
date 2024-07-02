package com.desafiobackend.desafiomobiauto.service;

import com.desafiobackend.desafiomobiauto.application.dtos.OportunidadeDTO;
import com.desafiobackend.desafiomobiauto.application.mapper.OportunidadeMapper;
import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import com.desafiobackend.desafiomobiauto.model.enums.TipoUsuario;
import com.desafiobackend.desafiomobiauto.model.repository.OportunidadeRepository;
import com.desafiobackend.desafiomobiauto.model.repository.UsuarioRepository;
import com.desafiobackend.desafiomobiauto.model.entity.Oportunidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OportunidadeService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OportunidadeRepository oportunidadeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RevendaService revendaService;

    public Oportunidade criarOportunidade(OportunidadeDTO oportunidadeDTO) throws Exception {
        Oportunidade novaOportunidade = OportunidadeMapper.INSTANCE.convertDtoToOportunidade(oportunidadeDTO);
        novaOportunidade = revendaService.atribuiOportunidadeRevenda(novaOportunidade);
        this.oportunidadeRepository.save(novaOportunidade);
        return novaOportunidade;
    }

    public List<Oportunidade> getAllOportunidades(){
        return this.oportunidadeRepository.findAll();
    }

    public void validaTipoUsuarioAssistente(Usuario usuario) throws Exception {
        Boolean isAssistente = usuario.getTipoUsuario().isAssistente();
        if( !isAssistente ){
            throw new Exception("Oportunidades só podem ser atribuídas a usuário do tipo assistente");
        }
    }

    public Oportunidade atribuirOportunidadeAoAssistente(OportunidadeDTO oportunidadeDTO) throws Exception {
        Oportunidade oportunidade = OportunidadeMapper.INSTANCE.convertDtoToOportunidade(oportunidadeDTO);

        Long idAssistente = oportunidade.getResponsavel().getId();
        Usuario assistente = usuarioRepository.findById(idAssistente)
                .orElseThrow(() -> new Exception("Assistente não encontrado com o ID: " + idAssistente));

        validaTipoUsuarioAssistente(assistente);

        oportunidade.setAtribuida(true);
        oportunidade.setResponsavel(assistente);
        oportunidade.setDataAtribuicao(LocalDateTime.now());
        this.oportunidadeRepository.save(oportunidade);
        return oportunidade;
    }


    public Oportunidade distribuirOportunidade(Oportunidade oportunidade) {
        List<Usuario> assistentesDisponiveis = usuarioRepository.findUsuarioByDisponivelTrue();

        if (assistentesDisponiveis.isEmpty()) {
            return null;
        }

        Usuario assistenteSelecionado = assistentesDisponiveis
                .stream()
                .sorted(Comparator.comparingInt((Usuario assistente) -> assistente.getOportunidades().size())
                        .thenComparing(this::getUltimaDataAtribuicao))
                .findFirst()
                .orElse(null);

        if (assistenteSelecionado == null) {
            return null;
        }

        assistenteSelecionado.setUltimaAtribuicao(LocalDateTime.now());

        oportunidade.setResponsavel(assistenteSelecionado);
        oportunidade.setDataAtribuicao(LocalDateTime.now());
        oportunidade.setAtribuida(true);
        assistenteSelecionado.addOportunidade(oportunidade);

        this.oportunidadeRepository.save(oportunidade);
        return oportunidade;
    }

    public void validaUsuarioTransferirOportunidade(TipoUsuario tipoUsuario) throws Exception {
        Boolean isAutorizadoTransferirOportunidade = tipoUsuario.isAutorizadoTransferirOportunidade();
        if( !isAutorizadoTransferirOportunidade ){
            throw new Exception("Transação não permitida para o usuário !");
        }
    }

    public Oportunidade transferirOportunidade(OportunidadeDTO transferirDTO) throws Exception {

        validaUsuarioTransferirOportunidade(transferirDTO.getTipoUsuarioAtivo());
        Oportunidade transferir = OportunidadeMapper.INSTANCE.convertDtoToOportunidade(transferirDTO);


        Long oportunidadeId = transferir.getId();
        Long novoAssistenteId = transferir.getResponsavel().getId();

        Oportunidade oportunidade = oportunidadeRepository.findById(oportunidadeId)
                .orElseThrow(() -> new Exception("Oportunidade não encontrada com o ID: " + oportunidadeId));

        Usuario novoAssistente = usuarioRepository.findById(novoAssistenteId)
                .orElseThrow(() -> new Exception("Assistente não encontrado com o ID: " + novoAssistenteId));

        oportunidade.setResponsavel(novoAssistente);
        oportunidade.setDataAtribuicao(LocalDateTime.now());

        return oportunidadeRepository.save(oportunidade);
    }

    public Oportunidade editarOportunidade(Long oportunidadeId, OportunidadeDTO oportunidadeDTO) throws Exception {
        Oportunidade oportunidade = oportunidadeRepository.findById(oportunidadeId)
                .orElseThrow(() -> new Exception("Oportunidade não encontrada com o ID: " + oportunidadeId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (!username.equals(oportunidade.getResponsavel().getUsername())) {
            if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                throw new AccessDeniedException("Você não tem permissão para editar esta oportunidade.");
            }
        }

        oportunidade.setDataConclusao(oportunidadeDTO.getDataConclusao());
        oportunidade.setStatusOportunidade(oportunidadeDTO.getStatusOportunidade());

        return oportunidadeRepository.save(oportunidade);
    }

    private LocalDateTime getUltimaDataAtribuicao(Usuario assistente) {
        return assistente.getOportunidades().stream()
                .map(Oportunidade::getDataAtribuicao)
                .max(Comparator.naturalOrder())
                .orElse(LocalDateTime.MIN);
    }
}
