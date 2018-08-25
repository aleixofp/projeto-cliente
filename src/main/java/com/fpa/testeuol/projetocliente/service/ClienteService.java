package com.fpa.testeuol.projetocliente.service;

import com.fpa.testeuol.projetocliente.data.ClienteRepository;
import com.fpa.testeuol.projetocliente.entity.cliente.ClienteDto;
import com.fpa.testeuol.projetocliente.entity.cliente.ClienteModel;

import com.fpa.testeuol.projetocliente.exception.ClienteNotFoundException;
import com.fpa.testeuol.projetocliente.exception.GeoSaveException;
import com.fpa.testeuol.projetocliente.exception.NomeClienteFormatException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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

    public ClienteDto insere(String nomeCliente) throws NomeClienteFormatException, GeoSaveException {

        if (!isNomeCorreto(nomeCliente)){
            throw new NomeClienteFormatException(String.format("Não foi possível inserir um novo cliente, causa: nome = %s", nomeCliente));
        }

        ClienteModel clienteModel = new ClienteModel(nomeCliente);

        try {
            // Gera os dados de geolocalização do cliente. (clima, cidade e etc.)
            ClienteGeoModel clienteGeoModel = servicoGeo.salvaDadosGeolocalizacao(clienteModel);
            clienteModel.setClienteGeo(clienteGeoModel);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new GeoSaveException("Não foi possível salvar os dados de geolocalização do cliente", e);
        }

        return new ClienteDto( repositorioCliente.save(clienteModel) );
    }

    public ClienteDto recupera(Long idCliente) throws ClienteNotFoundException {
        Optional<ClienteModel> cliente = this.repositorioCliente.findById(idCliente);

        if (!cliente.isPresent()){
            throw new ClienteNotFoundException(String.format("Cliente ID %d não encontrado.", idCliente));
        }

        return new ClienteDto( cliente.get() );
    }

    public void deleta(Long idCliente) throws ClienteNotFoundException {
        Optional<ClienteModel> cliente = repositorioCliente.findById(idCliente);
        if (!cliente.isPresent()){
            throw new ClienteNotFoundException(String.format("Não foi possível deletar cliente inexistente ID: %d", idCliente));
        }
        repositorioCliente.delete(cliente.get());
    }

    public List<ClienteDto> listaClientes(){
        return repositorioCliente.findAll()
                .stream()
                .map(ClienteDto::new)
                .collect(Collectors.toList());
    }

    public ClienteDto atualiza(Long idCliente, String novoNome) throws ClienteNotFoundException, NomeClienteFormatException {

        Optional<ClienteModel> clienteBase = repositorioCliente.findById(idCliente);
        if (!clienteBase.isPresent()){
            throw new ClienteNotFoundException(String.format("Não foi possível atualizar cliente ID %d: não encontrado.", idCliente));
        } else if (!isNomeCorreto(novoNome)){
            throw new NomeClienteFormatException(String.format("Não foi possível atualizar cliente ID %d: nome = %s", idCliente, novoNome));
        }

        ClienteModel clienteAtualizado = clienteBase.get();
        clienteAtualizado.setNome(novoNome);

        return new ClienteDto( repositorioCliente.save(clienteAtualizado) );
    }

    private boolean isNomeCorreto(String nome){
        return nome != null && !nome.isEmpty();
    }
}
