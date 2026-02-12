package com.whatsboard.dto;

import lombok.Data;

@Data
public class WhatsappMessageDTO {
    private String numero;
    private String nombre;
    private String mensaje;
    // Nuevos campos para la IA
    private String sentimiento;
    private String intencion;
    private String prioridad;
    private String categoria;
    private String resumen;
    private String timestamp;
}