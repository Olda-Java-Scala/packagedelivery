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

    private float price;

    @Override
    public int compareTo(DeliveryTargetDto dto) {
        return dto.getTotalWeight() == getTotalWeight() ? Math.round(dto.getTotalWeight())
                : Integer.compare(Math.round(dto.getTotalWeight()), Math.round(getTotalWeight()));
    }

    /** Adds weight for next package, which has to be delivered to target location
     * @param nextWeight
     */
    public void addPackageWeight(float nextWeight) {
        this.totalWeight = this.totalWeight + nextWeight;
        this.totalWeight = (float)Math.round(this.totalWeight * 1000f) / 1000f;
    }

    /** Adds price for next package, which has to be delivered to target location
     * @param nextPrice
     */
    public void addPrice(float nextPrice) {
        this.price = this.price + nextPrice;
        this.price = (float) Math.round(this.price * 100f) / 100f;
    }
}
