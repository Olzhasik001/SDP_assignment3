package com.medical.monitoring.sensor;
import com.medical.monitoring.model.HealthData;

public class HospitalBedSensor implements VitalSensor {
    @Override
    public HealthData readData() {
        return new HealthData(75, 98, 36.6);
    }
}