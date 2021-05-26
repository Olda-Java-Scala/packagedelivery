package com.homework.packagedelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This object serves for saving total weight of all packages, which should be delivered into specific postal code.
 */
@Data
@AllArgsConstructor
public class DeliveryTargetDto implements Comparable<DeliveryTargetDto> {

    private float totalWeight;

    private String postalCode;

    @Override
    public int compareTo(DeliveryTargetDto dto) {
        return Integer.compare(Math.round(dto.getTotalWeight()), Math.round(getTotalWeight()));
    }

    public void addPackageWeight(float newWeight) {
        this.totalWeight = this.totalWeight + newWeight;
        this.totalWeight = (float)Math.round(this.totalWeight * 1000f) / 1000f;
    }
}
