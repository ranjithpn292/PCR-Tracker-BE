package com.tracker.pcr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PCRData {
    @Id
    @GeneratedValue
    private String index;
    private double pcr;
    private long timestamp;

    public PCRData(String index, double pcr) {
        this.index = index;
        this.pcr = pcr;
        this.timestamp = System.currentTimeMillis();
    }
}
