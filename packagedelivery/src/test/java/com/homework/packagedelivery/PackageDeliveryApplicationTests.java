package com.homework.packagedelivery;

import com.homework.packagedelivery.dto.DeliveryTargetDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageDeliveryApplicationTests {

    @Test
    void addWeightToDtoTest() {
        float firstPackageWeight = 1.5f;
        String defaultPostalCode = "10001";

        DeliveryTargetDto dto = new DeliveryTargetDto(firstPackageWeight, defaultPostalCode);

        float secondPackageWeight = 2.3f;
        dto.addPackageWeight(secondPackageWeight);

        float expectedWeight = 3.8f;
        assertEquals(expectedWeight, dto.getTotalWeight());
    }

    @Test
    void roundingToThreeDecimalsTest() {
        float firstPackageWeight = 1.5f;
        String defaultPostalCode = "10001";

        DeliveryTargetDto dto = new DeliveryTargetDto(firstPackageWeight, defaultPostalCode);

        float secondPackageWeight = 2.301999f;
        dto.addPackageWeight(secondPackageWeight);

        float expectedWeight = 3.802f;
        assertEquals(expectedWeight, dto.getTotalWeight());
    }
}
