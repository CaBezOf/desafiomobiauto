package com.desafiobackend.desafiomobiauto.application.mapper;


import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import com.desafiobackend.desafiomobiauto.application.dtos.usuarios.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "senha", target = "senha")
    @Mapping(source = "tipoUsuario", target = "tipoUsuario")
    @Mapping(source = "revenda", target = "revenda")
    @Mapping(source = "oportunidade", target = "oportunidades")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "login", target = "login")
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "disponivel",  ignore = true)
    @Mapping(target = "ultimaAtribuicao", ignore = true)
    @Mapping(target = "quantidadeOportunidadesEmAndamento", ignore = true)
    Usuario convertDtoToUsuario(UsuarioDTO usuarioDTO);

}
