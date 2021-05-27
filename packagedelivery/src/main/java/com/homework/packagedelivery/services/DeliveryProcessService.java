package com.homework.packagedelivery.services;

import com.homework.packagedelivery.PackageDeliveryApplicationProperties;
import com.homework.packagedelivery.dto.DeliveryTargetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Whole process serves for loading inputs and splitting them into data required for counting delivery information,
 * which means, how many kilos would be delivered to specific postal code.
 */
@Service
@RequiredArgsConstructor
@EnableScheduling
public class DeliveryProcessService {

    // Stores postal codes and their complete weights of all packages
    private static final List<DeliveryTargetDto> deliveryTargets = Collections.synchronizedList(new ArrayList<>());

    private static final String NEW_LINE = System.lineSeparator();
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    PackageDeliveryApplicationProperties properties;

    /**
     * Starting method of whole process, where basic information is displayed to user, like welcome and rules for
     * entering data.
     */
    public void loadPackagesProcess() {
        System.out.println(properties.getWelcome());
        System.out.println(properties.getEnterText() + NEW_LINE +
                properties.getLineFormatInstructions() + NEW_LINE +
                properties.getLineExample());
        loadPackage();
    }

    /**
     * Recursive method which controls the process of loading weight and postal code of package.
     * This data is saved into List - List<DeliveryTargetDto> deliveryTargets.
     */
    private void loadPackage() {
        String inputLine = scanner.nextLine();
        if (inputLine.equals(properties.getQuitAppCommand())) {
            System.out.println(properties.getQuitAppText());
            return;
        }

        if (!rulesLineCheck(inputLine)) {
            System.out.println(properties.getInvalidLineText());
            loadPackage();
            return;
        }

        DeliveryTargetDto dto = setDeliveryTargetDto(inputLine);
        // this checks, if postal code has already been entered into list
        if (deliveryTargets.stream().anyMatch(target -> target.getPostalCode().equals(dto.getPostalCode()))) {
            // if postal code is already in list, this adds next weight of package to the postal code
            deliveryTargets.forEach(target -> {
                if (target.getPostalCode().equals(dto.getPostalCode())) {
                    target.addPackageWeight(dto.getTotalWeight());
                }
            });
        } else {
            deliveryTargets.add(dto);
        }
        loadPackage();
    }

    /** Method splits and saves data from input lane into dto.
     * @param inputLine
     * @return
     */
    public DeliveryTargetDto setDeliveryTargetDto(String inputLine) {
        float packageWeight = Float.parseFloat(inputLine.split(" ")[0]);
        String postalCoda = inputLine.split(" ")[1];
        return new DeliveryTargetDto(packageWeight, postalCoda);
    }

    /** Method checks, if entered line of data is correct. User can enter max weight 10kilos - same max value as Czech
     * Post is using.
     * @param inputLine
     * @return
     */
    public boolean rulesLineCheck(String inputLine) {
        Pattern pattern = Pattern.compile(properties.getRulesLinePattern());
        return pattern.matcher(inputLine).find();
    }

    /**
     *  Method check, if List of loaded data about packages is empty, if List is not empty, every minute, method writes
     *  into console simple table consisting of postal codes and weight of all packages, which will be delivered to it.
     */
    @Scheduled(cron = "0 * * * * *")
    public void loadedPackagesListing() {
        if (!deliveryTargets.isEmpty()) {
            TreeSet<DeliveryTargetDto> orderedPackages = new TreeSet<>(deliveryTargets);
            System.out.println();
            System.out.println(properties.getOutputFromStorage());
            orderedPackages.forEach(dto -> {
                System.out.println(dto.getPostalCode() + " " + dto.getTotalWeight());
            });
        }
    }
}
