package com.felipesouls.client.services;

import com.felipesouls.client.dto.ClientDTO;
import com.felipesouls.client.entities.Client;
import com.felipesouls.client.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> allClientPagined(Pageable pageable) {
        return clientRepository.findAll(pageable).map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id) {
        return new ClientDTO(clientRepository.findById(id).orElseThrow(() -> new RuntimeException()));
    }

    @Transactional
    public ClientDTO insertNewClient(ClientDTO clientDTO) {
        Client client = new Client(clientDTO);
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO updateClientPerId(Long id, ClientDTO clientDTO) {
        try {
            Client client = clientRepository.getReferenceById(id);
            converterDtoInEntity(client, clientDTO);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }

    public void deleteClientPerId(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        } catch (DataIntegrityViolationException se) {
            throw new DataIntegrityViolationException("");
        }
    }

    private void converterDtoInEntity(Client client, ClientDTO dto) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
