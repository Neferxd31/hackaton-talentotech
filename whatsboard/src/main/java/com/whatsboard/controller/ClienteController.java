package com.whatsboard.controller;

import com.whatsboard.model.Cliente;
import com.whatsboard.model.EstadoCliente;
import com.whatsboard.repository.ClienteRepository;
import com.whatsboard.service.ClienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;


    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        cliente.setEstado(EstadoCliente.NUEVO);
        cliente.setFechaCreacion(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}/estado")
    public Cliente cambiarEstado(@PathVariable Long id, @RequestParam EstadoCliente estado) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setEstado(estado);
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}/asignar/{agenteId}")
public Cliente asignarCliente(
        @PathVariable Long clienteId,
        @PathVariable Long agenteId) {

    return clienteService.asignarAgente(clienteId, agenteId);
}

}
