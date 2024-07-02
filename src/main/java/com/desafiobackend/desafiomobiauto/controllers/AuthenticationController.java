package com.desafiobackend.desafiomobiauto.controllers;

import com.desafiobackend.desafiomobiauto.application.dtos.usuarios.AuthenticationDTO;
import com.desafiobackend.desafiomobiauto.application.dtos.usuarios.LoginResponseDTO;
import com.desafiobackend.desafiomobiauto.application.dtos.usuarios.UsuarioDTO;
import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import com.desafiobackend.desafiomobiauto.model.repository.UsuarioRepository;
import com.desafiobackend.desafiomobiauto.infra.security.TokenService;
import com.desafiobackend.desafiomobiauto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Validated UsuarioDTO usuario) throws Exception {
        if(this.usuarioRepository.findByLogin(usuario.getLogin()) != null) return ResponseEntity.badRequest().build();
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
