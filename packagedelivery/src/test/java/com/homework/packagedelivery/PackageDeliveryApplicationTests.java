package com.homework.packagedelivery;

import com.homework.packagedelivery.dto.DeliveryTargetDto;
import com.homework.packagedelivery.services.DeliveryProcessService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageDeliveryApplicationTests {

    @Test
    void addWeightToDtoTest() {
        float firstPackageWeight = 1.5f;
        String defaultPostalCode = "10001";

        DeliveryTargetDto dto = new DeliveryTargetDto(firstPackageWeight, defaultPostalCode, 0);

        float secondPackageWeight = 2.3f;
        dto.addPackageWeight(secondPackageWeight);

        float expectedWeight = 3.8f;
        assertEquals(expectedWeight, dto.getTotalWeight());
    }

    @Test
    void roundingToThreeDecimalsTest() {
        float firstPackageWeight = 1.5f;
        String defaultPostalCode = "10001";

        DeliveryTargetDto dto = new DeliveryTargetDto(firstPackageWeight, defaultPostalCode, 0);

        float secondPackageWeight = 2.301999f;
        dto.addPackageWeight(secondPackageWeight);

        float expectedWeight = 3.802f;
        assertEquals(expectedWeight, dto.getTotalWeight());
    }

    @Test
    void ruleLinesTest() {
        DeliveryProcessService deliveryProcessService = new DeliveryProcessService();
        String correctLine = "3.4 08801";
        boolean result = deliveryProcessService.rulesLineCheck(correctLine);
        assertTrue(result);

        String incorrectLine = "3.4 088011";
        boolean resultOfIncorrectLine = deliveryProcessService.rulesLineCheck(incorrectLine);
        assertFalse(resultOfIncorrectLine);
    }

    @Test
    void lineSplittingTest() {
        DeliveryProcessService deliveryProcessService = new DeliveryProcessService();
        String correctLine = "3.4 08801";
        DeliveryTargetDto dto = deliveryProcessService.setDeliveryTargetDto(correctLine);
        assertEquals(3.4f, dto.getTotalWeight());
        assertEquals("08801", dto.getPostalCode());
    }
}
