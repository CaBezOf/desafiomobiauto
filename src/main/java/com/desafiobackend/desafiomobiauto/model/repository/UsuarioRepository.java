package com.desafiobackend.desafiomobiauto.model.repository;

import com.desafiobackend.desafiomobiauto.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

    List<Usuario> findUsuarioByDisponivelTrue();

    List<Usuario> findByDisponivelTrue();

    Optional<Usuario> findUsuarioById(Long id);
}
