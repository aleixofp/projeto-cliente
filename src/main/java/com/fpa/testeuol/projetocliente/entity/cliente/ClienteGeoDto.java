package com.fpa.testeuol.projetocliente.entity.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteGeoDto {

	private Long id;
	private Long idCliente;
	private String cidade;

	@JsonProperty("temperaturaMinima")
	private Float minTemp;
	@JsonProperty("temperaturaMaxima")
	private Float maxTemp;
	@JsonProperty("temperaturaAtual")
	private Float curTemp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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
