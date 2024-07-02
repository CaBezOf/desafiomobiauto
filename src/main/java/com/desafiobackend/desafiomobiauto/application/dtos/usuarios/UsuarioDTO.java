package com.desafiobackend.desafiomobiauto.application.dtos.usuarios;

import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import com.desafiobackend.desafiomobiauto.model.enums.TipoUsuario;
import com.desafiobackend.desafiomobiauto.model.entity.Oportunidade;
import com.desafiobackend.desafiomobiauto.model.entity.Revenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String login;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Revenda revenda;
    private List<Oportunidade> oportunidade;
    private Usuario usuario;

}
