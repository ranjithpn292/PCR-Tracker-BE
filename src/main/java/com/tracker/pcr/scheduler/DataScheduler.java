package com.tracker.pcr.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.tracker.pcr.model.PCRData;
import com.tracker.pcr.service.OptionChainService;
import com.tracker.pcr.service.PCRCalculationService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataScheduler {

    private final OptionChainService optionChainService;
    private final PCRCalculationService pcrService;

    public DataScheduler(
            OptionChainService optionChainService,
            PCRCalculationService pcrService) {

        this.optionChainService = optionChainService;
        this.pcrService = pcrService;
    }

    @Scheduled(fixedRate = 5000)
    public void trackPCR() {

        if(!isMarketOpen()) {
            System.out.println("Cannot show PCR data since Market is closed since time is"+ new Date()+ ", come back tomorrow at 09:15 AM");
            return;
        }
        calculateIndex("NIFTY");
        calculateIndex("BANKNIFTY");
    }

    private void calculateIndex(String symbol) {

        JsonNode data = optionChainService.fetchOptionChain(symbol);

        double pcr = pcrService.calculatePCR(data);

        PCRData result = new PCRData(symbol, pcr);

        System.out.println(
                symbol +
                        " PCR : " +
                        result.getPcr()
        );
    }

    private boolean isMarketOpen() {

        java.time.LocalTime now = java.time.LocalTime.now();

        java.time.LocalTime open = java.time.LocalTime.of(9,0);
        java.time.LocalTime close = java.time.LocalTime.of(15,30);

        return now.isAfter(open) && now.isBefore(close);
    }
}