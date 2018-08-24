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

            ClienteGeoModel clienteGeoModel = model.getGeo();
            if (this.clienteGeo == null){
                this.clienteGeo = new ClienteGeoDto();
                this.clienteGeo.setIdCliente(model.getId());

                if (clienteGeoModel != null){
                    this.clienteGeo.setCidade(clienteGeoModel.getCidade());
                    this.clienteGeo.setId(clienteGeoModel.getId());
                    this.clienteGeo.setCurTemp(clienteGeoModel.getCurTemp());
                    this.clienteGeo.setMinTemp(clienteGeoModel.getMinTemp());
                    this.clienteGeo.setMaxTemp(clienteGeoModel.getMaxTemp());
                }

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
