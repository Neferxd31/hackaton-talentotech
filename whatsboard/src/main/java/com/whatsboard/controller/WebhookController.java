package com.whatsboard.controller;

import com.whatsboard.dto.WhatsappMessageDTO;
import com.whatsboard.service.WhatsappService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
@RequiredArgsConstructor
@CrossOrigin
public class WebhookController {

    private final WhatsappService whatsappService;

    @PostMapping("/whatsapp")
    public void recibirMensaje(@RequestBody WhatsappMessageDTO dto) {
        whatsappService.procesarMensaje(dto);
    }

    // AÑADE ESTE NUEVO para el Frontend
@GetMapping("/mensajes")
public List<WhatsappMessageDTO> obtenerMensajes() {
    return whatsappService.listarTodos(); 
}
}
