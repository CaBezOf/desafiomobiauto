package com.desafiobackend.desafiomobiauto.model.entity;

import com.desafiobackend.desafiomobiauto.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status statusOportunidade = Status.NOVO;
    private String motivoConclusao;
    private Boolean atribuida = false;
    private LocalDateTime dataAtribuicao;
    private LocalDateTime dataConclusao;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "responsavel_id", referencedColumnName = "id")
    private Usuario responsavel;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "revenda_id", referencedColumnName = "id")
    private Revenda revenda;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    private Veiculo veiculo;


}
