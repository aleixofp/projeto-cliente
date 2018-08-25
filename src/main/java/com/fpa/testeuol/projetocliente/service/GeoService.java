package com.fpa.testeuol.projetocliente.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpa.testeuol.projetocliente.entity.cliente.ClienteModel;
import com.fpa.testeuol.projetocliente.entity.geo.ClimaDto;
import com.fpa.testeuol.projetocliente.entity.geo.VigilanteGeoDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteGeoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

    private Logger logger = LoggerFactory.getLogger(GeoService.class);

    private static final String IP_GEO_API_BASE_URL = "https://ipvigilante.com";
    private static final String WEATHER_GEO_API_BASE_URL = "https://www.metaweather.com/api";
    private static final String AMAZON_CHECK_IP_WS = "http://checkip.amazonaws.com/";
    private static final String FORMAT = "json";

    private ObjectMapper mapper;

    @Autowired
    public GeoService(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @SuppressWarnings("WeakerAccess")
    public ClienteGeoModel salvaDadosGeolocalizacao(ClienteModel cliente) throws IOException {

        logger.info("Salvando dados de Geolocalização do cliente");

        VigilanteGeoDto geoAtual = recuperaGeolocalizacaoAtual();
        String latitude = geoAtual.getLatitude();
        String longitude = geoAtual.getLongitude();

        logger.info("Latitude: " + latitude);
        logger.info("Longitude: " + longitude);
        logger.info("Cidade: " + geoAtual.getCityName());

        String localId = recuperaIdLocalizacao(latitude, longitude);

        ClimaDto clima = recuperaClimaLocalizacao(localId);

        ClienteGeoModel clienteGeoModel = new ClienteGeoModel();
        clienteGeoModel.setCliente(cliente);
        clienteGeoModel.setCidade(geoAtual.getCityName());
        clienteGeoModel.setMinTemp(Float.parseFloat(clima.getMinTemp()));
        clienteGeoModel.setMaxTemp(Float.parseFloat(clima.getMaxTemp()));
        clienteGeoModel.setCurTemp(Float.parseFloat(clima.getCurTemp()));

        return clienteGeoModel;
    }

    public VigilanteGeoDto recuperaGeolocalizacaoPorIp(String ip) throws IOException  {
        URL url = new URL(String.format("%s/%s/%s", IP_GEO_API_BASE_URL, FORMAT, ip));
        InputStream stream = url.openStream();
        HashMap data = mapper.readValue(stream, HashMap.class);
        String geoValues = mapper.writeValueAsString(data.get("data"));
        return mapper.readValue(geoValues, VigilanteGeoDto.class);
    }

    public VigilanteGeoDto recuperaGeolocalizacaoAtual() throws IOException {
        return recuperaGeolocalizacaoPorIp(recuperaIpMaquina());
    }

    public String recuperaIpMaquina() throws MalformedURLException {
        URL url = new URL(AMAZON_CHECK_IP_WS);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){

            String[] ips = br.readLine().replace(" ", "").split(",");
            int quantidadeIps = ips.length;
            if (quantidadeIps > 1)
                return ips[quantidadeIps -1];
            else
                return ips[0];
        } catch (IOException e){
            logger.error("Erro ao se conectar a API " + AMAZON_CHECK_IP_WS, e);
            return "0.0.0.0";
        }
    }

    public ClimaDto recuperaClimaLocalizacao(String woeId) throws IOException {
        URL url = new URL(String.format("%s/location/%s", WEATHER_GEO_API_BASE_URL, woeId));
        ClimaDto clima = new ClimaDto();
        InputStream stream = url.openStream();
        HashMap data = mapper.readValue(stream, HashMap.class);

        Map climaConfiavel = recuperaClimaConfiavel((List)data.get("consolidated_weather"));

        clima.setMinTemp(climaConfiavel.get("min_temp").toString());
        clima.setMaxTemp(climaConfiavel.get("max_temp").toString());
        clima.setCurTemp(climaConfiavel.get("the_temp").toString());

        return clima;
    }

    private String recuperaIdLocalizacao(String latitude, String longitude) throws IOException {
        URL url = new URL(String.format("%s/location/search/?lattlong=%s,%s", WEATHER_GEO_API_BASE_URL, latitude, longitude));
        InputStream stream = url.openStream();
        List data = mapper.readValue(stream, List.class);
        HashMap localidadePerto = (HashMap)data.get(0);
        return localidadePerto.get("woeid").toString();
    }

    private Map recuperaClimaConfiavel(List climasConsolidados){

        Map climaConfiavel = new HashMap();
        int maiorPrevisibilidade = 0;
        for(Object o: climasConsolidados){
            HashMap dados = (HashMap)o;
            Integer previsibilidade = Integer.parseInt(dados.get("predictability").toString());
            if (previsibilidade > maiorPrevisibilidade) {
                maiorPrevisibilidade = previsibilidade;
                climaConfiavel = dados;
            }
        }
        return climaConfiavel;
    }

}
