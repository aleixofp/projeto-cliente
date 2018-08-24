package com.fpa.testeuol.projetocliente.service;

import com.fpa.testeuol.projetocliente.data.ClienteRepository;
import com.fpa.testeuol.projetocliente.entity.cliente.ClienteDto;
import com.fpa.testeuol.projetocliente.entity.cliente.ClienteModel;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteGeoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private ClienteRepository repositorioCliente;
    private GeoService servicoGeo;

    @Autowired
    public ClienteService(ClienteRepository repositorioCliente, GeoService servicoGeo){
        this.repositorioCliente = repositorioCliente;
        this.servicoGeo = servicoGeo;
    }

    public ClienteDto insere(String nome) {
        if (nome == null || nome.isEmpty()){
            throw new NullPointerException("Não é possivel inserir cliente com este nome: {" + nome + "}");
        }
        ClienteModel clienteInserido = this.repositorioCliente.save(new ClienteModel(nome));

        try {
            ClienteGeoModel clienteGeo = servicoGeo.salvaDadosGeolocalizacao(clienteInserido);
            clienteInserido.setGeo(clienteGeo);
        } catch (IOException e) {
            logger.error("Não foi possivel salvar dados de geolocalização do cliente salvo: " + nome, e);
        }

        return new ClienteDto(clienteInserido);
    }

    public ClienteDto recupera(Long idCliente) {
        ClienteModel cliente = this.repositorioCliente.findById(idCliente).orElse(null);

        if (cliente == null){
            throw new NullPointerException("Não foi encontrado cliente com ID " + idCliente + " na base.");
        }

        return new ClienteDto(cliente);
    }

    public void deleta(Long idCliente) {
        repositorioCliente.deleteById(idCliente);
    }

    public List<ClienteDto> listaClientes(){
        return repositorioCliente.findAll()
                .stream()
                .map(ClienteDto::new)
                .collect(Collectors.toList());
    }
}
