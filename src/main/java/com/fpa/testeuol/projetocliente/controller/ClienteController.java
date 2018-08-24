package com.fpa.testeuol.projetocliente.controller;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteDto;
import com.fpa.testeuol.projetocliente.response.ClienteApiResponse;
import com.fpa.testeuol.projetocliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService servicoCliente;

    @Autowired
    public ClienteController(ClienteService servicoCliente) {
        this.servicoCliente = servicoCliente;
    }

    @GetMapping(value="/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteApiResponse> recuperar(@PathVariable("idCliente") Long idCliente){
        ClienteApiResponse response = new ClienteApiResponse("recuperar");
        try {
            ClienteDto dados = servicoCliente.recupera(idCliente);
            response.setMessage("Cliente ID " + idCliente + " recuperado.");
            response.setClienteDto(dados);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            response.setMessage("Não foi possível recuperar cliente ID " + idCliente);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping(value="/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteApiResponse> inserir(String nome){
        ClienteApiResponse response = new ClienteApiResponse("inserir");
        try {
            ClienteDto dadosInseridos = servicoCliente.insere(nome);
            response.setMessage("Cliente " + nome + " inserido.");
            response.setClienteDto(dadosInseridos);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            response.setMessage("Não foi possível inserir novo cliente.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping(value="/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteApiResponse> atualizar(){
        return null;
    }

    @DeleteMapping(value="/deletar/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletar(@PathVariable("idCliente") Long idCliente){
        return null;
    }


}
