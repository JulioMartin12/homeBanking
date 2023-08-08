package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.dtos.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping(value = "/clients")
    public List<ClientDTO> getClients(){
  List<ClientDTO> clients;
  clients = this.clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
  return  clients;


    }

    @RequestMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getclient(@PathVariable Long id){
        return clientRepository.findById(id)
                .map(ClientDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
