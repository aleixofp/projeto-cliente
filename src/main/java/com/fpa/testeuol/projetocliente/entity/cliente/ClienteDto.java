package com.fpa.testeuol.projetocliente.entity.cliente;

import com.fpa.testeuol.projetocliente.entity.geo.ClimaDto;
import org.springframework.beans.BeanUtils;

public class ClienteDto {

    private Long id;
    private String nome;
    private ClienteGeoDto clienteGeo;

    public ClienteDto(ClienteModel model){
        if (model != null) {
            BeanUtils.copyProperties(model, this);

            if (this.clienteGeo == null){
                this.clienteGeo = new ClienteGeoDto();
                BeanUtils.copyProperties(model.getGeo(), this.clienteGeo);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ClienteGeoDto getClienteGeo() {
        return clienteGeo;
    }

    public void setClienteGeo(ClienteGeoDto clienteGeo) {
        this.clienteGeo = clienteGeo;
    }
}
