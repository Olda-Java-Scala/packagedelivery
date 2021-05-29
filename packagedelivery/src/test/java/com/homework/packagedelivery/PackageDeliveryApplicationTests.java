package com.homework.packagedelivery;

import com.homework.packagedelivery.dto.DeliveryTargetDto;
import com.homework.packagedelivery.services.DeliveryProcessService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class PackageDeliveryApplicationTests {

    private static final String linePattern = "^(([0-9][.][0-9]{1,3})|([0-9]|(10))) [0-9]{5}$";

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
        boolean result = deliveryProcessService.rulesLineCheck(correctLine, linePattern);
        assertTrue(result);

        String incorrectLine = "3.4 088011";
        boolean resultOfIncorrectLine = deliveryProcessService.rulesLineCheck(incorrectLine, linePattern);
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
