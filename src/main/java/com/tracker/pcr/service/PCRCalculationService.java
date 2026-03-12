package com.tracker.pcr.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class PCRCalculationService {

    public double calculatePCR(JsonNode optionData) {

        double totalPutOI = 0;
        double totalCallOI = 0;

        if(optionData == null)
            return 0;

        for(JsonNode node : optionData) {

            if(node.has("CE")) {

                totalCallOI += node
                        .get("CE")
                        .get("openInterest")
                        .asDouble();
            }

            if(node.has("PE")) {

                totalPutOI += node
                        .get("PE")
                        .get("openInterest")
                        .asDouble();
            }

        }

        if(totalCallOI == 0)
            return 0;

        return totalPutOI / totalCallOI;
    }
}
