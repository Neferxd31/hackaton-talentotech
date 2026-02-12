package com.whatsboard.controller;

import com.whatsboard.model.EstadoCliente;
import com.whatsboard.repository.ClienteRepository;
import com.whatsboard.repository.AgenteRepository;
import com.whatsboard.repository.ConversacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin
public class StatsController {

    private final ClienteRepository clienteRepository;
    private final AgenteRepository agenteRepository;
    private final ConversacionRepository conversacionRepository;

    @GetMapping
    public Map<String, Object> obtenerEstadisticas() {

        long totalClientes = clienteRepository.count();
        long totalAgentes = agenteRepository.count();
        long totalConversaciones = conversacionRepository.count();

        long nuevos = clienteRepository.findAll().stream()
                .filter(c -> c.getEstado() == EstadoCliente.NUEVO)
                .count();

        long seguimiento = clienteRepository.findAll().stream()
                .filter(c -> c.getEstado() == EstadoCliente.EN_SEGUIMIENTO)
                .count();

        long cerrados = clienteRepository.findAll().stream()
                .filter(c -> c.getEstado() == EstadoCliente.CERRADO)
                .count();

        return Map.of(
                "totalClientes", totalClientes,
                "totalAgentes", totalAgentes,
                "totalConversaciones", totalConversaciones,
                "nuevos", nuevos,
                "enSeguimiento", seguimiento,
                "cerrados", cerrados
        );
    }
}
