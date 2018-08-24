package com.fpa.testeuol.projetocliente.response;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteDto;

public class ClienteApiResponse {

    private String serviceName;
    private String message;
    private ClienteDto clienteDto;

    public ClienteApiResponse(String serviceName, String message, ClienteDto clienteDto) {
        this.serviceName = serviceName;
        this.message = message;
        this.clienteDto = clienteDto;
    }

    public ClienteApiResponse(String service){
        this(service, "", null);
    }

    public ClienteApiResponse(){}

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }
}
