package com.cnsunru.common.model;

/**
 * Created by WQ on 2017/9/15.
 */

public class WeightCalculateMode {
    private float totalWeight, totalValue;

    public WeightCalculateMode(float totalWeight, float totalValue) {
        this.totalWeight = totalWeight;
        this.totalValue = totalValue;
    }

    public float getValForWeight(float weight) {
        return (weight / totalWeight) * totalValue;
    }
}
