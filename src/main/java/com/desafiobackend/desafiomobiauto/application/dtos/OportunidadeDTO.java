package com.desafiobackend.desafiomobiauto.application.dtos;

import com.desafiobackend.desafiomobiauto.model.entity.Revenda;
import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import com.desafiobackend.desafiomobiauto.model.enums.TipoUsuario;
import com.desafiobackend.desafiomobiauto.model.entity.Cliente;
import com.desafiobackend.desafiomobiauto.model.entity.Veiculo;
import com.desafiobackend.desafiomobiauto.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OportunidadeDTO {

    private Long id;
    private Status statusOportunidade;
    private String motivoConclusao;
    private LocalDateTime dataAtribuicao;
    private LocalDateTime dataConclusao;
    private Usuario responsavel;
    private Revenda revenda;
    private Cliente cliente;
    private Veiculo veiculo;
    private TipoUsuario tipoUsuarioAtivo;

}
