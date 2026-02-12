package com.whatsboard.service;

import com.whatsboard.model.Cliente;
import com.whatsboard.model.Agente;
import com.whatsboard.repository.ClienteRepository;
import com.whatsboard.repository.AgenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final AgenteRepository agenteRepository;

    public Cliente asignarAgente(Long clienteId, Long agenteId) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Agente agente = agenteRepository.findById(agenteId)
                .orElseThrow(() -> new RuntimeException("Agente no encontrado"));

        cliente.setAgente(agente);

        return clienteRepository.save(cliente);
    }
}
