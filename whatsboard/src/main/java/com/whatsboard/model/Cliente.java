package com.whatsboard.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String numero;

    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "agente_id")
    private Agente agente;
}
