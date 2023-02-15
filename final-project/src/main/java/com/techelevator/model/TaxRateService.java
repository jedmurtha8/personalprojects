package com.techelevator.model;

import com.techelevator.model.TaxRateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class TaxRateService {
    public static String API_BASE_URL = "https://teapi.netlify.app/api/statetax";
    private RestTemplate restTemplate = new RestTemplate();

    public double getTaxRate(String state){
        TaxRateResponse response = restTemplate.getForObject(API_BASE_URL + "?state=" + state, TaxRateResponse.class);
        return response.getTaxRate() / 100;
    }



}
