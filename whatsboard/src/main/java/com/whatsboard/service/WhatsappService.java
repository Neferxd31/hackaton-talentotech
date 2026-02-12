package com.whatsboard.service;

import com.whatsboard.dto.WhatsappMessageDTO;
import com.whatsboard.model.Cliente;
import com.whatsboard.model.Conversacion;
import com.whatsboard.model.EstadoCliente;
import com.whatsboard.repository.ClienteRepository;
import com.whatsboard.repository.ConversacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WhatsappService {

    private final ClienteRepository clienteRepository;
    private final ConversacionRepository conversacionRepository;

    public void procesarMensaje(WhatsappMessageDTO dto) {

        Cliente cliente = clienteRepository.findAll().stream()
                .filter(c -> c.getNumero().equals(dto.getNumero()))
                .findFirst()
                .orElseGet(() -> {
                    Cliente nuevo = new Cliente();
                    nuevo.setNombre(dto.getNombre());
                    nuevo.setNumero(dto.getNumero());
                    nuevo.setEstado(EstadoCliente.NUEVO);
                    nuevo.setFechaCreacion(LocalDateTime.now());
                    return clienteRepository.save(nuevo);
                });

        Conversacion conversacion = new Conversacion();
        conversacion.setCliente(cliente);
        conversacion.setMensaje(dto.getMensaje());
        conversacion.setFecha(LocalDateTime.now());
        conversacion.setAtendida(false);

        conversacionRepository.save(conversacion);
    }
}
