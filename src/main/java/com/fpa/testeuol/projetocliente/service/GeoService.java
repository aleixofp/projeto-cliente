package com.fpa.testeuol.projetocliente.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpa.testeuol.projetocliente.entity.geo.GeoDto;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

    private static final String IP_GEO_API_BASE_URL = "https://ipvigilante.com";
    private static final String WEATHER_GEO_API_BASE_URL = "https://www.metaweather.com/api";
    private static final String FORMAT = "json";

    private ObjectMapper mapper;

    @Autowired
    public GeoService(ObjectMapper mapper){
        this.mapper = mapper;
    }

    public GeoDto recuperaGeolocalizacaoPorIp(String ip) throws IOException {
        URL url = new URL(String.format("%s/%s/%s", IP_GEO_API_BASE_URL, FORMAT, ip));
        InputStream stream = url.openStream();
        HashMap data = mapper.readValue(stream, HashMap.class);
        String geoValues = mapper.writeValueAsString(data.get("data"));
        return mapper.readValue(geoValues, GeoDto.class);
    }

    public String recuperaIp() throws Exception {
        URL url = new URL("http://checkip.amazonaws.com/");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        return br.readLine();
    }

    public String recuperaIdLocalizacao(String latitude, String longitude) throws IOException {
        URL url = new URL(String.format("%s/location/search/?lattlong=%s,%s", WEATHER_GEO_API_BASE_URL, latitude, longitude));
        InputStream stream = url.openStream();
        List data = mapper.readValue(stream, List.class);
        HashMap localidadePerto = (HashMap)data.get(0);
        return localidadePerto.get("woeid").toString();
    }

}
