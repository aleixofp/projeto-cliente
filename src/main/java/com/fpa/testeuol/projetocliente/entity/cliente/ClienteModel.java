package com.fpa.testeuol.projetocliente.entity.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_geo_cliente")
    private ClienteGeoModel clienteGeo;

    public ClienteModel(){

    }

    public ClienteModel(String nome){
        this.nome = nome;
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

    public ClienteGeoModel getGeo() {
        return clienteGeo;
    }

    public void setGeo(ClienteGeoModel geo) {
        this.clienteGeo = geo;
    }
}
