package com.homework.packagedelivery.services;

import com.homework.packagedelivery.PackageDeliveryApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Service loads a file with fees for package weight, which are stored in memory - priceList, and then service can compute
 * fees for package weight.
 */
@AllArgsConstructor
@Component
public class PriceListService {

    // Stores fees for package weight, key is weight, value is price
    private final TreeMap<Float, Float> priceList = new TreeMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private static final String loadPriceListAgainPositiveInput = "Y";

    @Autowired
    PackageDeliveryApplicationProperties properties;
    @Autowired
    DeliveryProcessService deliveryProcessService;

    /** Method loads a file into BufferedReader stream.
     * @param fileName
     */
    public void loadPriceListFile(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            loadPricesFromFile(reader);
        } catch (FileNotFoundException e) {
            System.out.println(properties.getFileNotFoundText());
            String response = scanner.nextLine();
            if (response.equals(loadPriceListAgainPositiveInput))
                deliveryProcessService.loadPriceListRequest();
            else
                deliveryProcessService.cancelLoadingFile();
        }
    }

    /** Method loads key value pairs - key:weight, value:price into memory - TreeMap.
     * @param reader
     */
    private void loadPricesFromFile(BufferedReader reader) {
        for (String line : reader.lines().collect(Collectors.toList())) {
            if (!rulesLineCheck(line)) {
                System.out.println(properties.getPriceListInvalidLineText());
                String response = scanner.nextLine();
                if (response.equals(loadPriceListAgainPositiveInput)) {
                    deliveryProcessService.loadPriceListRequest();
                    break;
                }
                deliveryProcessService.cancelLoadingFile();
                break;
            }
            Float weight = Float.parseFloat(line.split(" ")[0]);
            Float price = Float.parseFloat(line.split(" ")[1]);
            priceList.put(weight, price);
        }
        System.out.println(properties.getFileLoadedOk());
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

    /** Method checks, if lines - in file with fees, has correct format.
     * @param inputLine
     * @return
     */
    public boolean rulesLineCheck(String inputLine) {
        Pattern pattern = Pattern.compile(properties.getFileRulesLinePattern());
        return pattern.matcher(inputLine).find();
    }
}
