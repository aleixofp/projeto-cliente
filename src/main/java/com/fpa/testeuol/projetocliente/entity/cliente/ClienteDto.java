package com.fpa.testeuol.projetocliente.entity.cliente;

import org.springframework.beans.BeanUtils;

public class ClienteDto {

    private Long id;
    private String nome;

    public ClienteDto(ClienteModel model){
        if (model != null) {
            BeanUtils.copyProperties(model, this);
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
}
