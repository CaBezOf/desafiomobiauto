package com.desafiobackend.desafiomobiauto.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Revenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cnpj;
    private String nomeSocial;
    @OneToMany(mappedBy = "revenda")
    private List<Usuario> usuarios;
    @OneToMany(mappedBy = "revenda")
    private List<Oportunidade> oportunidades;



}
