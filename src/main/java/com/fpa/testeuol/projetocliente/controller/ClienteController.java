package com.fpa.testeuol.projetocliente.controller;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteDto;
import com.fpa.testeuol.projetocliente.exception.ClienteNotFoundException;
import com.fpa.testeuol.projetocliente.exception.GeoSaveException;
import com.fpa.testeuol.projetocliente.exception.NomeClienteFormatException;
import com.fpa.testeuol.projetocliente.response.ClienteApiResponse;
import com.fpa.testeuol.projetocliente.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService servicoCliente;

    @Autowired
    public ClienteController(ClienteService servicoCliente) {
        this.servicoCliente = servicoCliente;
    }

    /**
     * Recupera um cliente (id e nome) pelo seu id na base.
     * @param idCliente - ID do cliente localizado na base.
     * @return
     */
    @GetMapping(value="/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> recuperar(@PathVariable("idCliente") Long idCliente){
        logger.info("Recuperando dados do cliente ID " + idCliente);

        try {
            ClienteDto dados = servicoCliente.recupera(idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(dados);
        } catch (ClienteNotFoundException cnfe){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Insere um cliente na base junto com seus dados de geolocalização.
     * @param cliente - Corpo de inserção (id, nome e geo)
     * @return
     */
    @PostMapping(value="/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> inserir(@RequestBody ClienteDto cliente){
        logger.info("Inserindo novo cliente...");

        try {
            ClienteDto dadosInseridos = servicoCliente.insere(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(dadosInseridos);
        } catch (NomeClienteFormatException e){
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        } catch (GeoSaveException gse){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    /**
     * Atualiza um cliente.
     * @param idCliente - ID do cliente localizado na base.
     * @param novoNome - Novo nome para qual será atribuido ao cliente.
     * @return
     */
    @PutMapping(value="/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> atualizar(@PathVariable("idCliente") Long idCliente, @RequestParam String novoNome)
    {
        logger.info(String.format("Atualizando cliente ID %d", idCliente));

        try {
            ClienteDto dadosAtualizados = servicoCliente.atualiza(idCliente, novoNome);
            return ResponseEntity.status(HttpStatus.OK).body(dadosAtualizados);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    /**
     * Deletar um cliente.
     * @param idCliente - ID do cliente localizado na base.
     * @return
     */
    @DeleteMapping(value="/deletar/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletar(@PathVariable("idCliente") Long idCliente){

        logger.info("Deletando cliente ID " + idCliente);

        try {
            servicoCliente.deleta(idCliente);
            return ResponseEntity.ok().build();
        } catch (ClienteNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /**
     * Lista todos os clientes no momento.
     * @return
     */
    @GetMapping(value="/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDto>> listar(){

        logger.info("Listando clientes");

        try {
            List<ClienteDto> clientes = servicoCliente.listaClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


}
