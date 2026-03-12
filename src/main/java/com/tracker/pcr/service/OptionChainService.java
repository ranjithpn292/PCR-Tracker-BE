package com.tracker.pcr.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OptionChainService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode fetchOptionChain(String symbol) {

        try {

            String url = "https://www.nseindia.com/api/option-chain-indices?symbol=" + symbol;

            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = mapper.readTree(response);

            return root.get("records").get("data");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}