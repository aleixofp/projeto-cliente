package com.fpa.testeuol.projetocliente.entity.cliente;

import com.fpa.testeuol.projetocliente.entity.geo.GeoModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "cliente_id_seq_gen", sequenceName = "cliente_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_seq_gen")
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_geo_cliente")
    private GeoModel geo;

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

    public GeoModel getGeo() {
        return geo;
    }

    public void setGeo(GeoModel geo) {
        this.geo = geo;
    }
}
