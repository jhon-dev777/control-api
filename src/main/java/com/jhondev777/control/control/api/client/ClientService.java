package com.jhondev777.control.control.api.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    };

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    };

    public ResponseEntity createClient(Client client) throws URISyntaxException  {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
    };

    public Client updateClient(Long id, Client client) {
        Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        if (client.getName() != null) currentClient.setName(client.getName());
        if (client.getEmail() != null ) currentClient.setEmail(client.getEmail());
        return this.clientRepository.save(currentClient);
    };

    public ResponseEntity deleteClient(Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    };

}
