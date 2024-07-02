package com.desafiobackend.desafiomobiauto.application.dtos.usuarios;


import com.desafiobackend.desafiomobiauto.model.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarDTO {

    private String login;
    private String senha;
    private TipoUsuario role;

}
