package com.fpa.testeuol.projetocliente.entity.cliente;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteModel;

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

@Entity
@Table(name = "cliente_geo")
public class ClienteGeoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_geo_cliente_seq")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name="id_geo_cliente_seq", sequenceName = "id_geo_cliente_seq")
	@Column(name = "id_geo_cliente")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_cliente")
	private ClienteModel cliente;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "temperatura_min")
	private Float minTemp;

	@Column(name = "temperatura_max")
	private Float maxTemp;

	@Column(name = "temperatura_atual")
	private Float curTemp;

	public ClienteGeoModel() {}

	public ClienteGeoModel(ClienteGeoDto clienteGeoDto){
		if (clienteGeoDto != null){
			BeanUtils.copyProperties(clienteGeoDto, this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Float minTemp) {
		this.minTemp = minTemp;
	}

	public Float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(Float maxTemp) {
		this.maxTemp = maxTemp;
	}

	public Float getCurTemp() {
		return curTemp;
	}

	public void setCurTemp(Float curTemp) {
		this.curTemp = curTemp;
	}
}
