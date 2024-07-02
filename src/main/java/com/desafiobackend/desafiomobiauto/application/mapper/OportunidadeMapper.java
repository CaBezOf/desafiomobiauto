package com.desafiobackend.desafiomobiauto.application.mapper;

import com.desafiobackend.desafiomobiauto.application.dtos.OportunidadeDTO;
import com.desafiobackend.desafiomobiauto.model.entity.Oportunidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OportunidadeMapper {
    OportunidadeMapper INSTANCE = Mappers.getMapper(OportunidadeMapper.class);

    @Mapping(source = "statusOportunidade", target = "statusOportunidade")
    @Mapping(target = "motivoConclusao", ignore = true)
    @Mapping(target = "dataAtribuicao", ignore = true)
    @Mapping(target = "dataConclusao", ignore = true)
    @Mapping(source = "responsavel", target = "responsavel")
    @Mapping(source = "revenda", target = "revenda")
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "veiculo", target = "veiculo")
    @Mapping(target = "atribuida", ignore = true)
    Oportunidade convertDtoToOportunidade(OportunidadeDTO oportunidadeDTO);
}
