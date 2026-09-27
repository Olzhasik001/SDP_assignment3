package com.medical.monitoring.sensor;
import com.medical.monitoring.model.HealthData;

public class WearableBandSensor implements VitalSensor {
    @Override
    public HealthData readData() {
        return new HealthData(72, 97, 36.8);
    }
}