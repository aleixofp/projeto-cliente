package com.fpa.testeuol.projetocliente.entity.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_geo")
public class ClienteGeoModel {

    @Id
    @GeneratedValue
    @Column(name="id_cliente_geo")
    private Long id;

    @OneToOne
    @JoinColumn(name="id_cliente")
    private ClienteModel cliente;



}
