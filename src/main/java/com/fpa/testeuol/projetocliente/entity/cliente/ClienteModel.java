package com.fpa.testeuol.projetocliente.entity.cliente;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_cliente_seq")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name="id_cliente_seq", sequenceName = "id_cliente_seq")
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_geo_cliente", nullable = false)
    private ClienteGeoModel clienteGeo;

    public ClienteModel(){

    }

    public ClienteModel(ClienteDto clienteDto){
        if (clienteDto != null){
            BeanUtils.copyProperties(clienteDto, this);
        }
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

    public ClienteGeoModel getClienteGeo() {
        return clienteGeo;
    }

    public void setClienteGeo(ClienteGeoModel clienteGeo) {
        this.clienteGeo = clienteGeo;
    }
}
