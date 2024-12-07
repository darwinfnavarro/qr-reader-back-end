package com.example.ProjectQr_reader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QrReaderService {

    private static final String API_URL_GET_IMEI = "http://apirlx.fleetport.com/api/Services/getIMEI";
    private static final String API_URL_GET_STORE =  "http://apirlx.fleetport.com/api/Services/getData/BODEGA";
    private static final String API_URL_GET_PLATFORM = "http://apirlx.fleetport.com/api/Services/getData/PLATAFORMA";

    @Autowired
    private RestTemplate restTemplate;

    public String getImei(String imei) {
        String url = API_URL_GET_IMEI + "/" + imei;

            return restTemplate.getForObject(url, String.class);

    }

    public String getStore() {
        return restTemplate.getForObject(API_URL_GET_STORE, String.class);
    }

    public String getPlatform() {
        return restTemplate.getForObject(API_URL_GET_PLATFORM, String.class);
    }






}
