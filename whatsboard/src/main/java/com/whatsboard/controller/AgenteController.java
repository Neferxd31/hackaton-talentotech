package com.whatsboard.controller;

import com.whatsboard.model.Agente;
import com.whatsboard.repository.AgenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agentes")
@RequiredArgsConstructor
@CrossOrigin
public class AgenteController {

    private final AgenteRepository agenteRepository;

    @GetMapping
    public List<Agente> listarAgentes() {
        return agenteRepository.findAll();
    }

    @PostMapping
    public Agente crearAgente(@RequestBody Agente agente) {
        return agenteRepository.save(agente);
    }
}
