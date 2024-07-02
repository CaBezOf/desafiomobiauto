package com.desafiobackend.desafiomobiauto.service;

import com.desafiobackend.desafiomobiauto.application.dtos.usuarios.UsuarioDTO;
import com.desafiobackend.desafiomobiauto.application.mapper.UsuarioMapper;
import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import com.desafiobackend.desafiomobiauto.model.enums.TipoUsuario;
import com.desafiobackend.desafiomobiauto.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RevendaService revendaService;


    public Usuario findUsuarioById(Long id) throws Exception {
        return this.usuarioRepository.findUsuarioById(id).orElseThrow(() -> new Exception("usuário não encontrado"));
    }

    public Usuario criarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDTO.getSenha());
        usuarioDTO.setSenha(encryptedPassword);
        Usuario novoUsuario = UsuarioMapper.INSTANCE.convertDtoToUsuario(usuarioDTO);
        if (usuarioDTO.getRevenda() != null ){
            novoUsuario = revendaService.atribuiUsuarioRevenda(novoUsuario);
        }
        usuarioAssistente(novoUsuario);

        return novoUsuario;
    }

    public void usuarioAssistente(Usuario usuario) {
        if (usuario.getTipoUsuario() == TipoUsuario.ASSISTENTE) {
            usuario.setDisponivel(true);
            usuario.setQuantidadeOportunidadesEmAndamento(0);
        }
    }

    public List<Usuario> getAllUsuarios(){
        return this.usuarioRepository.findAll();
    }
    public void salvarUsuarios(Usuario usuario){this.usuarioRepository.save(usuario);}



}
