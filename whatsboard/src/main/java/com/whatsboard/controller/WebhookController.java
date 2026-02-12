package com.whatsboard.controller;

import com.whatsboard.dto.WhatsappMessageDTO;
import com.whatsboard.service.WhatsappService;
import lombok.RequiredArgsConstructor;
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
}
