package com.fpa.testeuol.projetocliente.service;

import com.fpa.testeuol.projetocliente.data.ClienteRepository;
import com.fpa.testeuol.projetocliente.entity.cliente.ClienteDto;
import com.fpa.testeuol.projetocliente.entity.cliente.ClienteModel;
import com.fpa.testeuol.projetocliente.entity.geo.GeoDto;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository repositorioCliente;
    private GeoService servicoGeo;

    @Autowired
    public ClienteService(ClienteRepository repositorioCliente, GeoService servicoGeo){
        this.repositorioCliente = repositorioCliente;
        this.servicoGeo = servicoGeo;
    }

    public ClienteDto insere(String nome) throws Exception {

        String ipUsuario = servicoGeo.recuperaIp();
        GeoDto geo = servicoGeo.recuperaGeolocalizacaoPorIp(ipUsuario);

        String lat = geo.getLatitude();
        String lon = geo.getLongitude();

        System.out.println(servicoGeo.recuperaIdLocalizacao(lat, lon));

        if (nome == null || nome.isEmpty()){
            throw new RuntimeException("Não é possivel inserir cliente com este nome.");
        }
        ClienteModel clienteInserido = this.repositorioCliente.save(new ClienteModel(nome));

        return new ClienteDto(clienteInserido);
    }

    public ClienteDto recupera(Long idCliente) throws RuntimeException {
        ClienteModel cliente = this.repositorioCliente.findById(idCliente).orElse(null);

        if (cliente == null){
            throw new RuntimeException("Não foi encontrado cliente com ID " + idCliente + " na base.");
        }

        return new ClienteDto(cliente);
    }

}
