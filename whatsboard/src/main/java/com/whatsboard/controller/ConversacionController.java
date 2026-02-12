package com.whatsboard.controller;

import com.whatsboard.model.Conversacion;
import com.whatsboard.repository.ConversacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/conversaciones")
@RequiredArgsConstructor
@CrossOrigin
public class ConversacionController {

    private final ConversacionRepository conversacionRepository;

    @GetMapping
    public List<Conversacion> listarConversaciones() {
        return conversacionRepository.findAll();
    }

    @PostMapping
    public Conversacion crearConversacion(@RequestBody Conversacion conversacion) {
        conversacion.setFecha(LocalDateTime.now());
        conversacion.setAtendida(false);
        return conversacionRepository.save(conversacion);
    }
}
