package com.desafiobackend.desafiomobiauto.model.entity;

import com.desafiobackend.desafiomobiauto.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "revenda_id")
    private Revenda revenda;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<Oportunidade> oportunidades;

    // Propriedades específicas de Assistente
    private Boolean disponivel = true;
    private LocalDateTime ultimaAtribuicao;
    private int quantidadeOportunidadesEmAndamento;


    public Usuario(String login, String senha, TipoUsuario tipoUsuario){
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (TipoUsuario.ADMIN.isAdministrador()) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USUARIOS"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USUARIOS"))        ;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void addOportunidade(Oportunidade oportunidade) {
        this.quantidadeOportunidadesEmAndamento++;
        this.ultimaAtribuicao = LocalDateTime.now();
    }
}
