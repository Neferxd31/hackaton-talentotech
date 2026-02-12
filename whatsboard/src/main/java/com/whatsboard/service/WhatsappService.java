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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhatsappService {

    private final ClienteRepository clienteRepository;
    private final ConversacionRepository conversacionRepository;

    public void procesarMensaje(WhatsappMessageDTO dto) {
        // 1. Buscar o crear cliente
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

        // 2. Guardar conversación con datos de IA
        Conversacion conversacion = new Conversacion();
        conversacion.setCliente(cliente);
        conversacion.setMensaje(dto.getMensaje());
        conversacion.setFecha(LocalDateTime.now());
        conversacion.setAtendida(false);
        
        // Mapeo de datos que vienen de n8n
        conversacion.setSentimiento(dto.getSentimiento());
        conversacion.setIntencion(dto.getIntencion());
        conversacion.setPrioridad(dto.getPrioridad());
        conversacion.setResumen(dto.getResumen());

        conversacionRepository.save(conversacion);
    }

    public List<WhatsappMessageDTO> listarTodos() {
        return conversacionRepository.findAll().stream()
            .map(conv -> {
                WhatsappMessageDTO dto = new WhatsappMessageDTO();
                dto.setNumero(conv.getCliente().getNumero());
                dto.setNombre(conv.getCliente().getNombre());
                dto.setMensaje(conv.getMensaje());
                dto.setSentimiento(conv.getSentimiento());
                dto.setIntencion(conv.getIntencion());
                dto.setPrioridad(conv.getPrioridad());
                dto.setResumen(conv.getResumen());
                return dto;
            })
            .collect(Collectors.toList());
    }
}