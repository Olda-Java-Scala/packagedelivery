package com.homework.packagedelivery.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeMap;

@AllArgsConstructor
@Component
public class PriceListService {

    private final TreeMap<Float, Float> priceList = new TreeMap<>();

    public void loadPriceListFile(String fileName) {
        // example of fileName: D:\Stažené píčoviny\priceList
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            loadPricesFromFile(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPricesFromFile(BufferedReader reader) {
        reader.lines().forEach(line -> {
            Float weight = Float.parseFloat(line.split(" ")[0]);
            Float price = Float.parseFloat(line.split(" ")[1]);
            priceList.put(weight, price);
        });
    }

    public float calculatePriceForPackage(Float weight) {
        float targetKey = 0f;
        Set<Float> keySet = priceList.keySet();

        for (Float key : keySet) {
            if (weight < key || weight.equals(key)) {
                targetKey = key;
                break;
            }
        }
        return priceList.get(targetKey);
    }
}
