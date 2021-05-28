package com.homework.packagedelivery.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeMap;

/**
 * Service loads a file with fees for package weight, which are stored in memory - priceList, and then service can compute
 * fees for package weight.
 */
@AllArgsConstructor
@Component
public class PriceListService {

    private final TreeMap<Float, Float> priceList = new TreeMap<>();

    /** Method loads a file into BufferedReader stream.
     * @param fileName
     */
    public void loadPriceListFile(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            loadPricesFromFile(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Method loads key value pairs - key:weight, value:price into memory - TreeMap.
     * @param reader
     */
    private void loadPricesFromFile(BufferedReader reader) {
        reader.lines().forEach(line -> {
            Float weight = Float.parseFloat(line.split(" ")[0]);
            Float price = Float.parseFloat(line.split(" ")[1]);
            priceList.put(weight, price);
        });
    }

    /** Method calculates fees for package weight.
     * @param weight
     * @return
     */
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
