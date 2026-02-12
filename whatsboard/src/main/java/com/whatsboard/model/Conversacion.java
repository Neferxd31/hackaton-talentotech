package com.whatsboard.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fecha;

    private Boolean atendida;

    private String sentimiento;
private String intencion;
private String prioridad;
private String resumen;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
