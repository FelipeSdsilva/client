package com.felipesouls.client.controllers;

import com.felipesouls.client.dto.ClientDTO;
import com.felipesouls.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> getAllClientPagined(Pageable pageable) {
        return ResponseEntity.ok().body(clientService.allClientPagined(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> getClientPerId(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.findClientById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> postNewClient(@RequestBody ClientDTO clientDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clientService.insertNewClient(clientDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> putUpdateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok().body(clientService.updateClientPerId(id, clientDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCLient(@PathVariable Long id) {
        clientService.deleteClientPerId(id);
        return ResponseEntity.noContent().build();
    }
}
