package com.tracker.pcr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tracker.pcr.model.PCRData;

@Service
public class PCRService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendPCRData(PCRData data) {

        messagingTemplate.convertAndSend("/topic/pcr", data);

    }
}
