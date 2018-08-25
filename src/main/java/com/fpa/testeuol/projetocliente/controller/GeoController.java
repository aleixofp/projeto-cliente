package com.fpa.testeuol.projetocliente.controller;

import com.fpa.testeuol.projetocliente.entity.geo.ClimaDto;
import com.fpa.testeuol.projetocliente.entity.geo.VigilanteGeoDto;
import com.fpa.testeuol.projetocliente.service.GeoService;
import java.io.IOException;
import java.net.MalformedURLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
public class GeoController {

    private Logger logger = LoggerFactory.getLogger(GeoController.class);

    private GeoService servicoGeo;

    @Autowired
    public GeoController(GeoService servicoGeo){
        this.servicoGeo = servicoGeo;
    }

    @GetMapping(value="/localizacao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VigilanteGeoDto> recuperarGeolocalizacao(@RequestParam(required = false) String ip){
        try {

            VigilanteGeoDto geolocalizacao;
            if (ip == null || ip.isEmpty()){
                geolocalizacao = servicoGeo.recuperaGeolocalizacaoAtual();
            } else {
                geolocalizacao = servicoGeo.recuperaGeolocalizacaoPorIp(ip);
            }

            return ResponseEntity.ok(geolocalizacao);
        } catch (IOException e){
            logger.error("Problemas ao acessar serviço de geolocalização.", e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(value="/meu-ip", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> recuperarIp(){
        try {
            String meuIp = servicoGeo.recuperaIpMaquina();
            return ResponseEntity.ok(meuIp);
        } catch (MalformedURLException e) {
            logger.error("Problemas ao acessar serviço de recuperação de IP.", e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
